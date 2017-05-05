package handler;

import event.Event;
import scheduler.SchedulerPriorityBlockingQueue;

/**
 * Desciption: 处理事件的方法类。处理对应事件的方法的具体实现，应该尽可能不与事件绑定且应该是单实例的。
 * Author: JasonHan.
 * Creation time: 2017/03/28 11:14:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public abstract class Handler {
    /**
     * 处理事件的流程。事件对象会直接调用对应Handler子类的handle方法，所以需要在其中指定需要进行的操作。
     *
     * @param event
     * @return
     */
    public abstract int handle(Event event);

    protected abstract int createEvent(SchedulerPriorityBlockingQueue eventQueue, Object obj);

    protected abstract int dealEvent(SchedulerPriorityBlockingQueue eventQueue, Object obj);
}
