package com.example.fileio.orc;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Slf4j
class OrcFileReaderTest {

    @Test
    void read() throws Exception{
        OrcFileReader orcFileReader = new OrcFileReader();
        List<Map<String, Object>> rows = orcFileReader.readOrcGeneric(new Configuration(), "test-data/0621/test/crm_customer_offline");
        assertNotEquals(rows.size(),0);
        log.error("abc"+rows.size());
        for (Map<String, Object> row : rows) {
            System.out.println(row);
        }
    }

    @Test
    void createColumnReader() throws Exception{
    }

    @Test
    void readOrcGeneric()  throws Exception{
        OrcFileReader orcFileReader = new OrcFileReader();
        Configuration conf = new Configuration();
        conf.set("hive.exec.orc.default.row.index.stride","1000");
        conf.set("hive.orc.cache.stripe.details.size","200000");
        conf.set("hive.exec.orc.default.buffer.size","362144");
        List<Map<String, Object>> rows = orcFileReader.readOrcGeneric(conf, "src/test/resources/data_jumble_zip.orc");
        assertNotEquals(rows.size(),0);
        log.error(rows.size()+"");
        for (Map<String, Object> row : rows) {
            log.info(row.toString());
        }
    }
}