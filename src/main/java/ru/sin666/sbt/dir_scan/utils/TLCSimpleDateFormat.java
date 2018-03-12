package ru.sin666.sbt.dir_scan.utils;


import java.text.SimpleDateFormat;

public class TLCSimpleDateFormat extends SimpleDateFormatWrapper {

    private final String datePatternString;

    private final ThreadLocal<SimpleDateFormat> tlc = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(datePatternString);
        }
    };

    public TLCSimpleDateFormat(String datePattern) {
        this.datePatternString = datePattern;
    }

    protected SimpleDateFormat getTargetInstance(){
        return tlc.get();
    }

}
