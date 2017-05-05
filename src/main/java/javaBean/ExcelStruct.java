package javaBean;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/26 18:47:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class ExcelStruct extends FileStruct{

    private ArrayList<HashMap> data;
    private Workbook workbook;

    public ArrayList<HashMap> getData() {
        return data;
    }

    public void setData(ArrayList<HashMap> data) {
        this.data = data;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }
}
