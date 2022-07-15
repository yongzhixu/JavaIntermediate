package com.example.fileio.orc;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.exec.vector.*;
import org.apache.orc.OrcFile;
import org.apache.orc.Reader;
import org.apache.orc.RecordReader;
import org.apache.orc.TypeDescription;

import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;

// $example on:untyped_ops$
// col("...") is preferable to df.col("...")
import static org.apache.spark.sql.functions.col;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

@Slf4j
public class OrcFileReader {
    private static final int BATCH_SIZE = 12048;

    public List<Map<String, Object>> read(Configuration configuration, String path)
            throws IOException {
        // Create a list to collect rows
        List<Map<String, Object>> rows = new LinkedList<>();

        // Create an ORC reader using the Hadoop fileSystem and path
        try (Reader reader = OrcFile.createReader(new Path(path), OrcFile.readerOptions(configuration))) {
            // Extract the schema
            TypeDescription schema = reader.getSchema();

            try (RecordReader records = reader.rows(reader.options())) {
                // Read rows in batch for better performance.
                VectorizedRowBatch batch = reader.getSchema().createRowBatch(BATCH_SIZE);
                LongColumnVector orderIdColumnVector = (LongColumnVector) batch.cols[0];
                BytesColumnVector itemNameColumnVector = (BytesColumnVector) batch.cols[1];
                DoubleColumnVector priceColumnVector = (DoubleColumnVector) batch.cols[2];

                while (records.nextBatch(batch)) {
                    for (int rowNum = 0; rowNum < batch.size; rowNum++) {
                        // Read rows from the batch
                        Map<String, Object> map = new HashMap<>();
                        map.put("order_id", orderIdColumnVector.vector[rowNum]);
                        map.put("item_name", itemNameColumnVector.toString(rowNum));
                        map.put("price", priceColumnVector.vector[rowNum]);
                        rows.add(map);
                    }
                }
            }
        }
        return rows;
    }

    public List<Map<String, Object>> readOrcGeneric(Configuration configuration, String path)
            throws IOException {
        // Create a list to collect rows
        List<Map<String, Object>> rows = new LinkedList<>();

        // Create an ORC reader using the Hadoop fileSystem and path
        try (Reader reader = OrcFile.createReader(new Path(path), OrcFile.readerOptions(configuration))) {
            // Extract the schema and metadata from the reader
            TypeDescription schema = reader.getSchema();
            List<String> fieldNames = schema.getFieldNames();
            List<TypeDescription> columnTypes = schema.getChildren();

            // Get the column vector references
            int size = fieldNames.size();
            BiFunction<ColumnVector, Integer, Object>[] mappers = new BiFunction[size];
            for (int i = 0; i < size; i++) {
                TypeDescription type = columnTypes.get(i);
                mappers[i] = createColumnReader(type);
            }
            try (RecordReader records = reader.rows(reader.options())) {
                // Read rows in batch for better performance.
                VectorizedRowBatch batch = reader.getSchema().createRowBatch();
                while (records.nextBatch(batch)) {
                    for (int row = 0; row < batch.size; row++) {
                        // Read rows from the batch
                        Map<String, Object> map = new HashMap<>();
                        for (int col = 0; col < batch.numCols; col++) {
                            ColumnVector columnVector = batch.cols[col];
                            if (columnVector.isNull[row]) {
                                map.put(fieldNames.get(col), null);
                            } else {
                                Object value = mappers[col].apply(columnVector, row);
                                map.put(fieldNames.get(col), value);
                            }
                        }
                        rows.add(map);
                    }
                }
            }
        }
        return rows;
    }


    public BiFunction<ColumnVector, Integer, Object> createColumnReader(TypeDescription description) {
        // Reference: https://orc.apache.org/docs/core-java.html
        String type = description.getCategory().getName();
        BiFunction<ColumnVector, Integer, Object> mapper;
        if ("tinyint".equals(type)) {
            mapper = (columnVector, row) -> (byte) ((LongColumnVector) columnVector).vector[row];
        } else if ("smallint".equals(type)) {
            mapper = (columnVector, row) -> (short) ((LongColumnVector) columnVector).vector[row];
        } else if ("int".equals(type) || "date".equals(type)) {
            // Date is represented as int epoch days
            mapper = (columnVector, row) -> (int) ((LongColumnVector) columnVector).vector[row];
        } else if ("bigint".equals(type)) {
            mapper = (columnVector, row) -> ((LongColumnVector) columnVector).vector[row];
        } else if ("boolean".equals(type)) {
            mapper = (columnVector, row) -> ((LongColumnVector) columnVector).vector[row] == 1;
        } else if ("float".equals(type)) {
            mapper = (columnVector, row) -> (float) ((DoubleColumnVector) columnVector).vector[row];
        } else if ("double".equals(type)) {
            mapper = (columnVector, row) -> ((DoubleColumnVector) columnVector).vector[row];
        } else if ("decimal".equals(type)) {
            mapper = (columnVector, row) -> ((DecimalColumnVector) columnVector).vector[row].getHiveDecimal().bigDecimalValue();
        } else if ("string".equals(type) || type.startsWith("varchar")) {
            mapper = (columnVector, row) -> ((BytesColumnVector) columnVector).toString(row);
        } else if ("char".equals(type)) {
            mapper = (columnVector, row) -> ((BytesColumnVector) columnVector).toString(row).charAt(0);
        } else if ("timestamp".equals(type)) {
            mapper = (columnVector, row) -> ((TimestampColumnVector) columnVector).getTimestampAsLong(row);
        } else {
            throw new RuntimeException("Unsupported type " + type);
        }
        return mapper;
    }

    public void readOrcUsingSpark(String fileLocation) throws AnalysisException {
        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName(this.getClass().getName())
                .getOrCreate();
        Dataset<Row> df = spark.read().orc(fileLocation);

        // Displays the content of the DataFrame to stdout
        df.show();
        // $example on:untyped_ops$
        // Print the schema in a tree format
        df.printSchema();

        log.error(df.count() + "");
        // Select only the "name" column
//        df.select("name").show();
//
//        // Select everybody, but increment the age by 1
//        df.select(col("name"), col("age").plus(1)).show();
//        // $example on:run_sql$
//        // Register the DataFrame as a SQL temporary view
//        df.createOrReplaceTempView("people");
//
//        Dataset<Row> sqlDF = spark.sql("SELECT * FROM people");
//        sqlDF.show();
//
//        // $example on:global_temp_view$
//        // Register the DataFrame as a global temporary view
//        df.createGlobalTempView("people");
//
//        // Global temporary view is tied to a system preserved database `global_temp`
//        spark.sql("SELECT * FROM global_temp.people").show();
    }
}