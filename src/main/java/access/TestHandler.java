package access;

import entity.GetAddrList;
import entity.GetPDFAddr;
import event.GetAddrListEvent;
import event.GetPDFAddrEvent;
import handler.GetAddrListHandler;
import handler.GetPDFAddrHandler;
import scheduler.SchedulerPriorityBlockingQueue;

import java.util.Date;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/25 10:24:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class TestHandler {

    public static void main(String[] myArgs) {
//        GetAddrList getAddr = new GetAddrList("http://www.moh.gov.cn/zhuz/s9499/wsbz_2.shtml");
//        GetAddrListEvent getAddrListEvent = new GetAddrListEvent(new Date(), GetAddrListHandler.getInstance(), getAddr, new SchedulerPriorityBlockingQueue());
//        GetAddrListHandler getAddrListHandler = (GetAddrListHandler) GetAddrListHandler.getInstance();
//        getAddrListHandler.handle(getAddrListEvent);


        GetPDFAddr getPdfAddr = new GetPDFAddr("http://www.moh.gov.cn/zhuz/s9491/wsbz.shtml",
                "http://www.moh.gov.cn/zhuz/s9499/201412/d169bcb7fb674c89999ada218d2f40cb.shtml");
        GetPDFAddrEvent getPDFAddrEvent = new GetPDFAddrEvent(new Date(), GetAddrListHandler.getInstance(), getPdfAddr, new SchedulerPriorityBlockingQueue());
        GetPDFAddrHandler getPDFAddrHandler = (GetPDFAddrHandler) GetPDFAddrHandler.getInstance();
        getPDFAddrHandler.handle(getPDFAddrEvent);

    }
}
