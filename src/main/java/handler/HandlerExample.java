package handler;

import event.Event;
import scheduler.SchedulerPriorityBlockingQueue;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/21 15:57:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class HandlerExample extends Handler {

    private HandlerExample() {}

    /**
     * 配合getInstance方法，实现安全的多线程环境下的单实例创建。
     * 类级的内部类，只有被调用的时候才会被加载，从而实现了延迟加载。
     */
    private static class HandlerExampleHolder {
        private static HandlerExample HANDLER_EXAMPLE_HOLDER_INSTANCE = new HandlerExample();
    }

    public static Handler getInstance() {
        return HandlerExampleHolder.HANDLER_EXAMPLE_HOLDER_INSTANCE;
    }

    public int handle(Event event) {
        return 0;
    }

    @Override
    protected int dealEvent(SchedulerPriorityBlockingQueue eventQueue, Object event) {
        return 0;
    }

    @Override
    protected int createEvent(SchedulerPriorityBlockingQueue eventQueue, Object event) {
        return 0;
    }
}
