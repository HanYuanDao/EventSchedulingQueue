package handler;

import event.Event;
import event.MakeExcelEvent;
import entity.ExcelStruct;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import resource.ExcelResource;
import scheduler.SchedulerPriorityBlockingQueue;

import java.io.IOException;
import java.util.*;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/25 09:47:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class LoadDataFromExcelHandler extends Handler {

    private LoadDataFromExcelHandler() {}

    /**
     * 配合getInstance方法，实现安全的多线程环境下的单实例创建。
     * 类级的内部类，只有被调用的时候才会被加载，从而实现了延迟加载。
     */
    private static class LoadDataFromExcelHolder {
        private static LoadDataFromExcelHandler LOAD_DATA_FROM_EXCEL_HOLDER_INSTANCE = new LoadDataFromExcelHandler();
    }

    public static LoadDataFromExcelHandler getInstance() {
        return LoadDataFromExcelHolder.LOAD_DATA_FROM_EXCEL_HOLDER_INSTANCE;
    }

    @Override
    public int handle(Event event) {
        System.out.println("处理LoadDataFromExcelHandler");
        try {
            ExcelStruct excelStruct = (ExcelStruct) event.getData();
            excelStruct.setWorkbook(ExcelResource.loadExcel(excelStruct.getFilePath()));
            excelStruct.setData(loadValue(excelStruct.getWorkbook()));

            createEvent(event.getEventQueue(), excelStruct);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected int createEvent(SchedulerPriorityBlockingQueue eventQueue, Object obj) {
        MakeExcelEvent makeExcelEvent = new MakeExcelEvent(new Date(), MakeExcelHandler.getInstance(), obj, eventQueue);
        eventQueue.insertEvent(makeExcelEvent);
        return 0;
    }

    @Override
    protected int dealEvent(SchedulerPriorityBlockingQueue eventQueue, Object obj) {
        return 0;
    }

    private ArrayList<HashMap> loadValue(Workbook workbook) {
        ArrayList<HashMap> result = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0);
        System.out.println("sheet.getLastRowNum():"+sheet.getLastRowNum());
        for (Row rowStep : sheet) {
            HashMap step = new HashMap();

            int column = 0;
            for (Cell cellStep : rowStep) {
                switch (cellStep.getCellType()) {
                    case HSSFCell.CELL_TYPE_BLANK:
                        step.put(column++, "");
                        break;
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        step.put(column++, cellStep.getBooleanCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_ERROR:
                        step.put(column++, cellStep.getErrorCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA:
                        step.put(column++, cellStep.getCellFormula());
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        step.put(column++, cellStep.getNumericCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        step.put(column++, cellStep.getStringCellValue());
                        break;
                    default:
                        break;
                }
            }

            result.add(step);
        }

        return result;
    }

}
