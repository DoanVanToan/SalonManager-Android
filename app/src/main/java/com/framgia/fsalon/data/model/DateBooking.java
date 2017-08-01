package com.framgia.fsalon.data.model;

import com.framgia.fsalon.FSalonApplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.framgia.fsalon.utils.Constant.DATE_FORMAT_MM_DD_YYYY;
import static com.framgia.fsalon.utils.Constant.DAY_OF_WEEK_FORMAT;

/**
 * Created by MyPC on 20/07/2017.
 */
public class DateBooking {
    private String mDay;
    private String mDayOfWeek;
    private String mDateTime;
    private long mTimeMillis;

    public DateBooking(String day, long timeMillis) {
        mDay = day;
        mTimeMillis = timeMillis / 1000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        SimpleDateFormat formatDayOfWeek = new SimpleDateFormat(DAY_OF_WEEK_FORMAT, Locale.US);
        setDayOfWeek(formatDayOfWeek.format(calendar.getTime()));
        SimpleDateFormat formatDate = new SimpleDateFormat(DATE_FORMAT_MM_DD_YYYY, Locale.US);
        setDateTime(formatDate.format(calendar.getTime()));
    }

    public String getDay() {
        return mDay;
    }

    public void setDay(String day) {
        mDay = day;
    }

    public String getDayOfWeek() {
        return mDayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        mDayOfWeek = dayOfWeek;
    }

    public String getDateTime() {
        return mDateTime;
    }

    public void setDateTime(String dateTime) {
        mDateTime = dateTime;
    }

    public long getTimeMillis() {
        return mTimeMillis;
    }

    public void setTimeMillis(long timeMillis) {
        mTimeMillis = timeMillis;
    }

    private String getStringRes(int resId) {
        return FSalonApplication.getInstant().getResources().getString(resId);
    }
}
