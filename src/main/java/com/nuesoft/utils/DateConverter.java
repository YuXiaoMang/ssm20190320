package com.nuesoft.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {


    /**
    * @Author 余承怿
    * @Description //TODO 
    * @Date 9:09 2019/6/24
    * @Param [date]
    * @return java.util.Date
    **/
    @Override
    public Date convert(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        if (date!=null){
            try {
                return dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println("a");
        return null;
    }



   
}
