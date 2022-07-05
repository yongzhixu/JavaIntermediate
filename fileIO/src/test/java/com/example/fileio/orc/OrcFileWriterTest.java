package com.example.fileio.orc;


import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OrcFileWriterTest {

    @Test
    void write() throws Exception {
        deleteFile("orders.orc");
        OrcFileWriter orcFileWriter = new OrcFileWriter();
        List<Map<String, Object>> data = new LinkedList<>();

//       ========================FOR JAVA 9 or higher====================================
//        data.add(Map.of("order_id", 1, "item_name", "Laptop", "price", 800.0f));
//        data.add(Map.of("order_id", 2, "item_name", "Mouse", "price", 150.0f));
//        data.add(Map.of("order_id", 3, "item_name", "Keyboard", "price", 250.0f));
        data.add(new HashMap<String, Object>() {{
            put("order_id", 1);
            put("item_name", "Laptop");
            put("price", 1220.0f);
        }});
        data.add(new HashMap<String, Object>() {{
            put("order_id", 2);
            put("item_name", "Mouse");
            put("price", 150.0f);
        }});
        data.add(new HashMap<String, Object>() {{
            put("order_id", 3);
            put("item_name", "Keyboard");
            put("price", 9990.0f);
        }});
        try {
//            orcFileWriter.write(new Configuration(), "orders.orc", "struct<order_id:int,item_name:string,price:float>", data);
            orcFileWriter.writeOrcGeneric(new Configuration(), "orders.orc", "struct<order_id:int,item_name:string,price:float>", data);
        }catch (Exception e){

        }
        System.out.println("Done");
    }

    private void deleteFile(String path) throws IOException {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        } catch (DirectoryNotEmptyException e) {
            System.out.println("Directory is not empty.");
        } catch (IOException e) {
            System.out.println("Invalid permissions.");
        }
        System.out.println("Deletion successful.");
    }

    @Test
    void createColumnWriter() {
    }
}