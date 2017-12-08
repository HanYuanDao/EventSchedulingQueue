package resource;

import entity.FileStruct;
import org.apache.commons.lang.StringUtils;
import tool.StringHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import static tool.StringHelper.changeCharset;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/25 14:15:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class FileResource {

    private static final String FILE_CODE = (String) System.getProperties().get("file.encoding");

    protected static String getExcelPath() throws IOException {
        String excelFilePath = null;
        Properties properties = new Properties();

        InputStream in = FileResource.class.getResourceAsStream("../excel.properties");
        properties.load(in);
        in.close();

        //遍历配置文件方法一
        Enumeration en = properties.propertyNames();
        while (en.hasMoreElements()) {
            String key = en.nextElement().toString();
            String value = properties.getProperty(key);
            System.out.println(key + ":" + value);
            if (key.equals("fileName")) {
                excelFilePath = value;
            }
        }
        //遍历配置文件方法二
//        Set keys = props.keySet();
//        for (Interator it = keys.iterator(); it.hasNext();){
//            String k = it.next();
//            System.out.println(k + ":" + props.getProperty(k));
//        }
        return changeCharset(excelFilePath, FILE_CODE);
    }

    public static ArrayList<File> getFileList(String folderPath) throws IOException {
        ArrayList<File> result = new ArrayList<File>();

        if (null == folderPath) {
            folderPath = getTargetFolderPath();
        }

        File f = new File(folderPath);
        File[] files = f.listFiles();
        for (File file : files) {
            if(file.isDirectory()) {
                result.addAll(getFileList(file.getAbsolutePath()));
            } else {
                result.add(file);
            }
        }
        return result;
    }

    public static ArrayList<FileStruct> getFilePathList(String folderPath) throws IOException {
        ArrayList<FileStruct> result = new ArrayList<FileStruct>();

        if (folderPath.equals("null")||StringUtils.isBlank(folderPath)) {
            System.out.println("folderPath1:"+folderPath);
            folderPath = getTargetFolderPath();
        }

        System.out.println("folderPath2:"+folderPath);
        File f = new File(folderPath);
        File[] files = f.listFiles();
        for (File file : files) {
            if(file.isDirectory()) {
                result.addAll(getFilePathList(file.getAbsolutePath()));
            } else {
                FileStruct fileStruct = new FileStruct();
                fileStruct.setFileName(file.getName());
                fileStruct.setFilePath(file.getPath());
                result.add(fileStruct);
            }
        }
        return result;
    }

    private static String getTargetFolderPath() throws IOException {
        String folderPath = "";

        Properties properties = new Properties();
        InputStream in = FileResource.class.getResourceAsStream("../excel.properties");
        properties.load(in);
        in.close();

        //遍历配置文件方法一
        Enumeration en = properties.propertyNames();
        while (en.hasMoreElements()) {
            String key = en.nextElement().toString();
            String value = properties.getProperty(key);
            System.out.println(key + ":" + value);
            if (key.equals("folderPath")) {
                folderPath = value;
            }
        }
        return folderPath;
    }
}
