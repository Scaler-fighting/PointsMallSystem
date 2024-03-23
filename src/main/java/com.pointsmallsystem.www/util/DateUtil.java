package com.pointsmallsystem.www.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    public static Date converStringToDate(String dateString) throws ParseException {
        return sdf.parse(dateString);
    }
    public static String convertDateToString(Date date){
        return sdf.format(date);
    }
}
