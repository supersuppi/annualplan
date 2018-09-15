package com.gxh.apserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date convertFromStringTODate(String dateToConvert) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date formattedDate = null;
        try {
            formattedDate = sdf.parse(dateToConvert);
        } catch (ParseException e) {
            throw e;
        }
        return formattedDate;
    }

    public static String getNowDateInString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(new Date());
    }

    public static String convertDateTOString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }
}
