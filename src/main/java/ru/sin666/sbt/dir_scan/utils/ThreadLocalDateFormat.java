package ru.sin666.sbt.dir_scan.utils;

import java.text.SimpleDateFormat;

public class ThreadLocalDateFormat extends ThreadLocal<SimpleDateFormat> {

    private final String datePatternString;

    public ThreadLocalDateFormat(String datePatternString) {
        this.datePatternString = datePatternString;
    }

    @Override
    protected SimpleDateFormat initialValue() {
        return new SimpleDateFormat(datePatternString);
    }
}
