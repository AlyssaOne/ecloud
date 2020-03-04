package com.bjfu.ecloud.common;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

//unused
public class Log4jAppender extends DailyRollingFileAppender {

    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        //只判断是否相等，而不判断优先级
        return this.getThreshold().equals(priority);
    }
}
