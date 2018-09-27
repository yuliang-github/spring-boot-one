package com.yl.other.test;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Alex
 * @since 2018/9/27 11:10
 */
public class DelayBean implements Delayed {

    private long msgId;

    private long timeOut;

    public DelayBean(long msgId,long timeOut){
        this.timeOut = timeOut + System.currentTimeMillis();
        this.msgId = msgId;
    }

    /**
     * 只有getDelay方法到期才能从队列中被取出
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return this.timeOut - System.currentTimeMillis();
    }

    /**
     * 比较排序,小的放在队头
     */
    @Override
    public int compareTo(Delayed o) {
        return Long.valueOf(this.timeOut).compareTo(((DelayBean)o).timeOut);
    }

    @Override
    public String toString() {
        return "DelayBean{" +
            "msgId=" + msgId +
            ", timeOut=" + timeOut +
            '}';
    }
}
