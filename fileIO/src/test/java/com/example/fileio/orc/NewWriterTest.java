package com.example.fileio.orc;


import org.apache.hadoop.conf.Configuration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

class NewWriterTest {

    private final static String BASE_FILE = "test-data/0621/test/";

    /**
     * 模拟测试
     * crm会员
     */
    @Test
    public void writeCrmCustomer() {
        String fileName = BASE_FILE + "crm_customer_offline";
        String structText = "struct<_id:string,mbr_id:string,mbr_mobile:string,mini_prog_open_id_1:string,mini_prog_open_id_2:string,mp_open_id_1:string,mp_open_id_2:string,union_id:string,nickname:string,province:string,city:string,mbr_sex:string,mbr_birth:string,mbr_marriage_status:string,brand_name:string,regist_src:string,referee_mbr_id:string,mbr_shop_name:string,regist_explain:string,mbr_level_name:string,mbr_state:string,mbr_level_start_time:string,mbr_level_stop_time:string,mbr_point_cnt:string,fav_cat_2_name_1y:string,fav_cat_2_amt_1y:decimal(22,5),2nd_fav_cat_2_name_1y:string,cat_2_cnt_1y:bigint,regist_time:string,last_login_time:string,msg_create_time:string,msg_update_time:string,ts:string,data_type:string,overdue_point_cnt:bigint,data_source:string,dt:string>";
        int minNum = 1000 * 10000;
        int maxNum = 1300 * 10000;
        List<Map<String, Object>> mapList = this.getListCrmCustomer(minNum/10000, maxNum/10000);
        commonWrite(fileName, mapList, structText);
    }

    public void commonWrite(String fileName, List<Map<String, Object>> mapList, String structText) {
//        deleteFile(fileName);
        OrcFileWriter orcFileWriter = new OrcFileWriter();
        try {
            Configuration configuration = new Configuration();
            configuration.setBoolean("orc.overwrite.output.file",true);
            orcFileWriter.writeOrcGeneric(configuration, fileName, structText, mapList);
            System.out.println("write success");
        } catch (Exception e) {
            System.out.println("write false");
            e.printStackTrace();
        }
    }

    public void deleteFile(String path) {
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

    /**
     * crm会员
     *
     * @return List
     */
    private List<Map<String, Object>> getListCrmCustomer(int minNum, int maxNum) {
        List<Map<String, Object>> mapList = new LinkedList<>();
        for (int i = minNum; i < maxNum; i++) {
            int id = i + 1;
            if (id % 10000 == 0) {
                System.out.println("record:" + (id / 10000));
            }
            mapList.add(new HashMap<String, Object>(64) {{
                put("2nd_fav_cat_2_name_1y", "");
                put("_id", "qm_" + id);
                put("brand_name", "全棉时代");
                put("cat_2_cnt_1y", 0);
                put("city", "深圳");
                put("data_source", "私域");
                put("data_type", "离线");
                put("dt", "2012-12-12 00:00:00");
                put("fav_cat_2_amt_1y", BigDecimal.valueOf(0));
                put("fav_cat_2_name_1y", "");
                put("last_login_time", "2012-12-12 19:19:19");
                put("mbr_birth", "2008-08-08");
                put("mbr_id", "qm_" + id);
                put("mbr_level_name", "棉苗");
                put("mbr_level_start_time", "2012-12-12 11:11:11");
                put("mbr_level_stop_time", "2012-12-12 13:13:13");
                put("mbr_marriage_status", "未婚");
                put("mbr_mobile", "133" + id);
                put("mbr_point_cnt", "100");
                put("mbr_sex", "F");
                put("mbr_shop_name", "");
                put("mbr_state", "正常");
                put("mini_prog_open_id_1", "mp_open_id1_" + id);
                put("mini_prog_open_id_2", "mp_open_id2_" + id);
                put("mp_open_id_1", "mini_open_id2_" + id);
                put("mp_open_id_2", "mini_open_id2_" + id);
                put("msg_create_time", "2012-12-12 14:14:14");
                put("msg_update_time", "2012-12-12 17:17:17");
                put("nickname", "test_" + id);
                put("overdue_point_cnt", 20);
                put("province", "广东");
                put("referee_mbr_id", "");
                put("regist_explain", "");
                put("regist_src", "EC");
                put("regist_time", "2009-12-31 12:00:00");
                put("ts", "2012-12-12 18:18:18");
                put("union_id", "union_id_" + id);
            }});
        }
        return mapList;
    }
}