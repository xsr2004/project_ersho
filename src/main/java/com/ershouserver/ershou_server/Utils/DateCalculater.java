package com.ershouserver.ershou_server.Utils;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author：xsr
 * @name：DataCalculater
 * @Date：2023/4/22 14:30
 * @Filename：DataCalculater
 */
@Component
public class DateCalculater {

    public static Date GetNeedTimeDistance(int distance){
        Date now = new Date();
        // 创建日历对象并设置为当前时间
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        // 将日历对象的日期减去7天，即得到一周前的时间
        cal.add(Calendar.DATE, distance);
        return cal.getTime();
    }

}
