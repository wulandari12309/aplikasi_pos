package com.system.aplikasi.pos.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
public class CommonUtils {

    public Date setHour(int hour){
        Calendar from = Calendar.getInstance();
        from.set(Calendar.HOUR_OF_DAY, hour);
        from.set(Calendar.MINUTE, 0);
        from.set(Calendar.SECOND, 0);
        return from.getTime();
    }

    public Date configHourGMT7(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 7);
        return cal.getTime();
    }

    public Date addhour(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 23);
        cal.add(Calendar.MINUTE, 23);
        return cal.getTime();
    }
}
