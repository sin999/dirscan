package ru.sin666.sbt.dir_scan.utils;

import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class SimpleDateFormatWrapper extends SimpleDateFormat{

    protected abstract SimpleDateFormat getTargetInstance();

    @Override
    public void set2DigitYearStart(Date startDate) {
        getTargetInstance().set2DigitYearStart(startDate);
    }

    @Override
    public Date get2DigitYearStart() {
        return getTargetInstance().get2DigitYearStart();
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
        return getTargetInstance().format(date, toAppendTo, pos);
    }

    @Override
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        return getTargetInstance().formatToCharacterIterator(obj);
    }

    @Override
    public Date parse(String text, ParsePosition pos) {
        return getTargetInstance().parse(text, pos);
    }

    @Override
    public String toPattern() {
        return getTargetInstance().toPattern();
    }

    @Override
    public String toLocalizedPattern() {
        return getTargetInstance().toLocalizedPattern();
    }

    @Override
    public void applyPattern(String pattern) {
        getTargetInstance().applyPattern(pattern);
    }

    @Override
    public void applyLocalizedPattern(String pattern) {
        getTargetInstance().applyLocalizedPattern(pattern);
    }

    @Override
    public DateFormatSymbols getDateFormatSymbols() {
        return getTargetInstance().getDateFormatSymbols();
    }

    @Override
    public void setDateFormatSymbols(DateFormatSymbols newFormatSymbols) {
        getTargetInstance().setDateFormatSymbols(newFormatSymbols);
    }

    @Override
    public Object clone() {
        return getTargetInstance().clone();
    }

    @Override
    public int hashCode() {
        return getTargetInstance().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return getTargetInstance().equals(obj);
    }

    @Override
    public Date parse(String source) throws ParseException {
        return getTargetInstance().parse(source);
    }

    @Override
    public Object parseObject(String source, ParsePosition pos) {
        return getTargetInstance().parseObject(source, pos);
    }

    public static Locale[] getAvailableLocales() {
        return DateFormat.getAvailableLocales();
    }

    @Override
    public void setCalendar(Calendar newCalendar) {
        getTargetInstance().setCalendar(newCalendar);
    }

    @Override
    public Calendar getCalendar() {
        return getTargetInstance().getCalendar();
    }

    @Override
    public void setNumberFormat(NumberFormat newNumberFormat) {
        getTargetInstance().setNumberFormat(newNumberFormat);
    }

    @Override
    public NumberFormat getNumberFormat() {
        return getTargetInstance().getNumberFormat();
    }

    @Override
    public void setTimeZone(TimeZone zone) {
        getTargetInstance().setTimeZone(zone);
    }

    @Override
    public TimeZone getTimeZone() {
        return getTargetInstance().getTimeZone();
    }

    @Override
    public void setLenient(boolean lenient) {
        getTargetInstance().setLenient(lenient);
    }

    @Override
    public boolean isLenient() {
        return getTargetInstance().isLenient();
    }

//    @Override
//    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
//        return getTargetInstance().format(obj, toAppendTo, pos);
//    }

    @Override
    public Object parseObject(String source) throws ParseException {
        return getTargetInstance().parseObject(source);
    }
}
