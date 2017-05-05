package scheduler;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Desciption: This is event queue.
 * Author: JasonHan.
 * Creation time: 2017/03/28 11:21:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class SchedulerPriorityBlockingQueue<Event> extends PriorityBlockingQueue<Event> {

    /**
     * Insert a event
     * @param event
     * @return
     */
    public boolean insertEvent(Event event){
        return super.offer(event);
    }

    /**
     * Get the first event in the queue.
     * @return
     */
    public Event getEvent(){
        return super.peek();
    }

    /**
     * Delete the first event in the queue.
     * @return
     */
    public Event removeEvent(){
        return super.poll();
    }
}
