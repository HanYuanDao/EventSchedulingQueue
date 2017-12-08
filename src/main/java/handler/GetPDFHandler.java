package handler;

import event.Event;
import scheduler.SchedulerPriorityBlockingQueue;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/12/08 10:32:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class GetPDFHandler extends Handler {
    private GetPDFHandler() {

    }

    /**
     * 配合getInstance方法，实现安全的多线程环境下的单实例创建。
     * 类级的内部类，只有被调用的时候才会被加载，从而实现了延迟加载。
     */
    private static class HandlerExampleHolder {
        private static GetPDFHandler HANDLER_EXAMPLE_HOLDER_INSTANCE = new GetPDFHandler();
    }

    public static Handler getInstance() {
        return HandlerExampleHolder.HANDLER_EXAMPLE_HOLDER_INSTANCE;
    }

    @Override
    public int handle(Event event) {
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
