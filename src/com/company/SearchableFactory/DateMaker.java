package com.company.SearchableFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMaker {
    /**
     * Factory method for handling creation of a Date object
     * (a single date or a range)
     * @param date The date, expressed as a String
     * @return The Date object
     * @throws Exception If the date created is invalid
     */
    public Date makeDate(String date) throws Exception{
        if(date.length()> 11){
            return new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(date);

        }

        String[] dateFields = date.split("-");
        Date dates;
        if(dateFields.length == 3){
            dates = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }
        else if(dateFields.length == 2){
            dates = new SimpleDateFormat("yyyy-MM").parse(date);
        }
        else{
            dates = new SimpleDateFormat("yyyy").parse(date);
        }
        return dates;
    }
}
