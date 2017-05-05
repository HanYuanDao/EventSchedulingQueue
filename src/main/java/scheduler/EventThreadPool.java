package scheduler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Desciption: The thread pool is used to store the handler of the event to be processed.
 * Author: JasonHan.
 * Creation time: 2017/03/31 15:05:00.
 * © Copyright 2013-2017, Node Supply Chain Management.
 */
public class EventThreadPool extends ThreadPoolExecutor{

    private static final int CPU_CORE_NUM = Runtime.getRuntime().availableProcessors(); //The number of cpu cores.

    private static final int CORE_POOL_SIZE = 2 * CPU_CORE_NUM; //The number of running thread.
    private static final int MAX_POOL_SIZE= Integer.MAX_VALUE; //Maximum thread pool size.
    private static final int KEEP_ALIVE_TIME = 180; //The alive time of thread.
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS; //空闲线程的超时时间单位
    private static final BlockingQueue QUEUE = new LinkedBlockingQueue();

    private static EventThreadPool EVENT_THREAD_POOL_INSTANCE;

    private EventThreadPool(int corePoolSize, int maxPoolSize, int keepAlivTime, TimeUnit keepAlivTimeUnit, BlockingQueue queue) {
        super(corePoolSize, maxPoolSize, keepAlivTime, keepAlivTimeUnit, queue);
        if (null != EVENT_THREAD_POOL_INSTANCE) {
            throw new RuntimeException("TestHandler's Instance is has been created");
        }
        EVENT_THREAD_POOL_INSTANCE = this;
    }

    public static EventThreadPool getThreadPool() {
        if (null == EVENT_THREAD_POOL_INSTANCE) {
            EVENT_THREAD_POOL_INSTANCE = new EventThreadPool(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, QUEUE);
        }
        return EVENT_THREAD_POOL_INSTANCE;
    }

    public void execute(Runnable command) {
        super.execute(command);
    }

    public int getActiveCount() {
        return super.getActiveCount();
    }

    public long getCompletedTaskCount() {
        return super.getCompletedTaskCount();
    }

    public long getTaskCount() {
        return super.getTaskCount();
    }
}
