package ExceptionsProgramming;

import java.io.File;
import java.io.IOException;


public class FileCheck {

    /**
     * if the file does not exist, create it
     * @param file
     */
    public void isFileExists(File file) {

        if (file.exists()) {
            System.out.println("file already exists");
        } else {
            System.out.println("file not exists, create it ...");
            try {
                File fileParent = file.getParentFile();  
                if(!fileParent.exists()){  
                    fileParent.mkdirs();  
                }
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * if the directory does not exist, create it
     * @param file
     */
    public void isDirExists(File file) {

        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir already exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }

    }
}
