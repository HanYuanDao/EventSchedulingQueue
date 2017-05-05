package event;

import handler.Handler;
import scheduler.SchedulerPriorityBlockingQueue;

import java.util.Date;

/**
 * Desciption:
 * Author: JasonHan.
 * Creation time: 2017/04/25 09:47:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class LoadDataFromExcelEvent extends Event {
    /**
     * Allocates a new {@code Event} object.
     *
     * @param doTime     {@link #doTime} is used to specify the execution time.
     *                   If the event queue have more than one event in the main program a polling(sleep) time
     *                   or old event still in the event queue,
     *                   the event will not be executed on time.
     * @param handler    {@link #handler} is used to specify the processing method.
     * @param data       {@link #data} is used to save data that needs to be processed or transmitted
     * @param eventQueue
     */
    public LoadDataFromExcelEvent(Date doTime, Handler handler, Object data, SchedulerPriorityBlockingQueue eventQueue) {
        super(doTime, handler, data, eventQueue);
    }
}
