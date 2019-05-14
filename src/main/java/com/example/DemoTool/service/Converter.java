package com.example.DemoTool.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Converter {

    /**
     * method to convert a character string (in yyyy-MM-dd format) to date in milliseconds
     *
     * @param choosenDate String
     * @return dateInLong
     * long milliseconds (between Epoch and choosenDate)
     */
    public long convertDateToLong(String choosenDate) {
        DateFormat formatter;
        Date date;
        long dateInLong = 0;
        try {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.parse(choosenDate);
            dateInLong = date.getTime();
        } catch (ParseException e) {
            System.out.println(e);
        }
        return dateInLong;
    }

    /**
     * method for converting an hour (format HH:mm) into a long
     *
     * @param choosenTime String
     * @return timeInLong
     * time in long at 01/01/1970
     * @throws ParseException
     */
    public long convertTimeToLong(String choosenTime) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date d = dateFormat.parse(choosenTime);
        long timeInLong = d.getTime();
        return timeInLong;
    }

    /**
     * method to obtain a date thanks to a long
     *
     * @param longDate long
     * @return dateText
     * date in String in the format dd/MM/yyyy
     */
    public String convertLongToStringDate(long longDate) {
        Date date = new Date(longDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy '-' HH:mm");
        String dateText = dateFormat.format(date);
        return dateText;
    }
}
