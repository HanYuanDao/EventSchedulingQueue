package resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/25 14:17:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class ExcelResource {

    /*public static ArrayList<HSSFWorkbook> loadExcel() throws IOException {
        ArrayList<HSSFWorkbook> hssfWorkbookList = new ArrayList<>();
        ArrayList<String> result = new ArrayList<String>();

        String excelFilePath = FileResource.getExcelPath();
        System.out.println("excelFilePath:"+excelFilePath);
        if (null!=excelFilePath) {
//            InputStream in = ExcelResource.class.getResourceAsStream(excelFilePath);
            ArrayList<File> fileList= FileResource.getFileList(null);
            FileInputStream in = null;
            for (File step : fileList) {
                in = new FileInputStream(step.getPath());
                hssfWorkbookList.add(new HSSFWorkbook(in));
            }
            if (null!=in) {
                in.close();
            }
        }

        return hssfWorkbookList;
    }*/

    public static Workbook loadExcel(String filePath) throws IOException {
        String[] filePathArr = filePath.split("[.]");
        String tailStr = filePathArr[1];
        if (tailStr.equals("xls")) {
            return loadHSSFExcel(filePath);
        } else {
            return loadXSSFExcel(filePath);
        }
    }

    public static HSSFWorkbook loadHSSFExcel(String filePath) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        ArrayList<String> result = new ArrayList<String>();

        System.out.println("excelFilePath:"+filePath);
        if (null != filePath) {
            FileInputStream in = new FileInputStream(filePath);
            hssfWorkbook = new HSSFWorkbook(in);
            if (null!=in) {
                in.close();
            }
        }

        return hssfWorkbook;
    }

    private static XSSFWorkbook loadXSSFExcel(String filePath) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        ArrayList<String> result = new ArrayList<String>();

        System.out.println("excelFilePath:"+filePath);
        if (null != filePath) {
            FileInputStream in = new FileInputStream(filePath);
            xssfWorkbook = new XSSFWorkbook(in);
            if (null!=in) {
                in.close();
            }
        }

        return xssfWorkbook;
    }
}
