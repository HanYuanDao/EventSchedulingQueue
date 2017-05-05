package handler;

import event.Event;
import event.LoadDataFromExcelEvent;
import javaBean.ExcelStruct;
import javaBean.FileStruct;
import resource.FileResource;
import scheduler.SchedulerPriorityBlockingQueue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/26 16:54:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class LoadFilePathHandler extends Handler {
    private static LoadFilePathHandler LOAD_FILE_PATH_HANDLER_INSTANCE = new LoadFilePathHandler();

    private LoadFilePathHandler() {}

    public static LoadFilePathHandler getInstance() {
        return LOAD_FILE_PATH_HANDLER_INSTANCE;
    }

    @Override
    public int handle(Event event) {
        System.out.println("处理LoadFilePathHandler");
        ArrayList<FileStruct> filePathList = null;
        try {
            filePathList = FileResource.getFilePathList(String.valueOf(event.getData()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        createEvent(event.getEventQueue(), filePathList);
        return 0;
    }

    @Override
    protected int createEvent(SchedulerPriorityBlockingQueue eventQueue, Object obj) {
        ArrayList<FileStruct> filePathList = null;
        if (null != obj) {
            filePathList = (ArrayList<FileStruct>) obj;
        }
        if (null != filePathList) {
            Date nowTime = new Date();
            for (FileStruct step : filePathList) {
                ExcelStruct excelStruct = new ExcelStruct();
                excelStruct.setFilePath(step.getFilePath());
                excelStruct.setFileName(step.getFileName());
                LoadDataFromExcelEvent loadDataFromExcelEvent =
                        new LoadDataFromExcelEvent(
                                nowTime, LoadDataFromExcelHandler.getInstance(), excelStruct, eventQueue);
                eventQueue.insertEvent(loadDataFromExcelEvent);
            }
        }

        return 0;
    }

    @Override
    protected int dealEvent(SchedulerPriorityBlockingQueue eventQueue, Object obj) {
        return 0;
    }

}
