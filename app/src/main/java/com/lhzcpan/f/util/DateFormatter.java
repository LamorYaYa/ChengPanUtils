package com.lhzcpan.f.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author master
 * @date 2018/1/25
 */

public class DateFormatter {

    /**
     * 知乎时间格式化
     *
     * @param date
     * @return
     */
    public String ZhihuDailyDateFormat(long date) {
        String sDate;
        Date d = new Date(date + 24 * 60 * 60 * 1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        sDate = format.format(d);
        return sDate;
    }

    /**
     * 豆瓣时间格式化
     *
     * @param date
     * @return
     */
    public String DoubanDateFormat(long date) {
        String sDate;
        Date d = new Date(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        sDate = format.format(d);
        return sDate;
    }
}
