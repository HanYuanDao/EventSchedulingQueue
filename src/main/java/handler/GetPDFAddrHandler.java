package handler;

import access.GetAddr;
import entity.GetAddrList;
import entity.GetPDFAddr;
import event.Event;
import event.GetAddrListEvent;
import event.GetPDFAddrEvent;
import http.HttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import scheduler.SchedulerPriorityBlockingQueue;

import java.io.IOException;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/12/08 10:16:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class GetPDFAddrHandler extends Handler {
    private GetPDFAddrHandler() {

    }

    /**
     * 配合getInstance方法，实现安全的多线程环境下的单实例创建。
     * 类级的内部类，只有被调用的时候才会被加载，从而实现了延迟加载。
     */
    private static class HandlerExampleHolder {
        private static GetPDFAddrHandler HANDLER_EXAMPLE_HOLDER_INSTANCE = new GetPDFAddrHandler();
    }

    public static Handler getInstance() {
        return HandlerExampleHolder.HANDLER_EXAMPLE_HOLDER_INSTANCE;
    }


    @Override
    public int handle(Event event) {
        GetPDFAddrEvent getPDFAddrEvent = (GetPDFAddrEvent)event;
        GetPDFAddr getPDFAddr = (GetPDFAddr)getPDFAddrEvent.getData();

        String htmlResult = "";
        try {
            htmlResult = HttpClient.accessUrl(getPDFAddr.getAimUrl());
            Document doc = Jsoup.parse(htmlResult);

            Element step = doc.getElementsByClass("con").get(0);
            String a = step.getElementsByTag("a").get(0).attr("href");
            System.out.println(GetAddr.URL_BASE + "../.." + a);


        } catch (IOException e) {
            System.out.println("Exception：PDF所在页面打开失败。" + getPDFAddrEvent.toString()
                    + " -----" + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception：PDF所在页面解析html失败。" + getPDFAddrEvent.toString()
                + " -----" + e.getMessage() + "-----" + htmlResult);
        }


        return 0;
    }

    @Override
    protected int createEvent(SchedulerPriorityBlockingQueue eventQueue, Object obj) {
        return 0;
    }

    @Override
    protected int dealEvent(SchedulerPriorityBlockingQueue eventQueue, Object obj) {
        return 0;
    }
}
