package com.gxh.apserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date convertFromStringTODate(String dateToConvert) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedDate = null;
        try {
            formattedDate = sdf.parse(dateToConvert);
        } catch (ParseException e) {
            throw e;
        }
        return formattedDate;
    }

    public static String getNowDateInString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}
