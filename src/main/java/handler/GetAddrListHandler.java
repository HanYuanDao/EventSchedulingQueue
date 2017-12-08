package handler;

import access.GetAddr;
import entity.GetAddrList;
import entity.GetPDFAddr;
import event.Event;
import event.GetAddrListEvent;
import event.GetPDFAddrEvent;
import http.HttpClient;
import org.json.HTTP;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import scheduler.SchedulerPriorityBlockingQueue;

import java.io.IOException;
import java.util.Date;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/12/08 10:31:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class GetAddrListHandler extends Handler {
    private GetAddrListHandler() {

    }

    /**
     * 配合getInstance方法，实现安全的多线程环境下的单实例创建。
     * 类级的内部类，只有被调用的时候才会被加载，从而实现了延迟加载。
     */
    private static class HandlerExampleHolder {
        private static GetAddrListHandler HANDLER_EXAMPLE_HOLDER_INSTANCE = new GetAddrListHandler();
    }

    public static Handler getInstance() {
        return HandlerExampleHolder.HANDLER_EXAMPLE_HOLDER_INSTANCE;
    }

    @Override
    public int handle(Event event) {
        GetAddrListEvent getAddrListEvent = (GetAddrListEvent)event;
        GetAddrList getAddrList = (GetAddrList)getAddrListEvent.getData();

        try {
            String htmlResult = HttpClient.accessUrl(getAddrList.getUrl());
            if (null != htmlResult) {
                Document doc = Jsoup.parse(htmlResult);

                for (Element step : doc.getElementsByClass("xx")) {
                    String a = step.getElementsByTag("a").get(0).attr("href");
                    //System.out.println(GetAddr.URL_BASE + a);

                    GetPDFAddr getPDFAddr = new GetPDFAddr(getAddrList.getUrl(), GetAddr.URL_BASE + a);
                    GetPDFAddrEvent getPDFAddrEvent = new GetPDFAddrEvent(new Date(), GetPDFAddrHandler.getInstance(), getPDFAddr, getAddrListEvent.getEventQueue());
                    getAddrListEvent.getEventQueue().insertEvent(getPDFAddrEvent);
                }
            } else {
                System.out.println("Exception: 页面url不存在\n" + getAddrListEvent.toString());
            }

        } catch (IOException e) {
            System.out.println("Exception：" + getAddrListEvent.toString()
                    + " -----" + e.getMessage());
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
