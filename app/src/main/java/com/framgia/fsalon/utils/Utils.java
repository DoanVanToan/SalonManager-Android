package com.framgia.fsalon.utils;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.screen.scheduler.SchedulerViewModel;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;

import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_TODAY;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_TOMORROW;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_YESTERDAY;
import static com.framgia.fsalon.utils.Constant.ApiParram.OUT_OF_INDEX;

/**
 * Created by MyPC on 20/07/2017.
 */
public class Utils {
    public static <T> Observable<T> getResponse(Respone<T> listRespone) {
        if (listRespone == null) {
            return Observable.error(new NullPointerException());
        } else if (listRespone.isError()) {
            String msgError = "";
            for (String msg : listRespone.getMessage()) {
                msgError += msg + " ";
            }
            return Observable.error(new Throwable("ERROR: " + msgError));
        } else {
            return Observable.just(listRespone.getData());
        }
    }

    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    public static int createTimeStamp(@SchedulerViewModel.TabFilter int tab) {
        Calendar calendar = Calendar.getInstance();
        switch (tab) {
            case TAB_TODAY:
                return (int) (calendar.getTime().getTime() / 1000);
            case TAB_TOMORROW:
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                return (int) (calendar.getTime().getTime() / 1000);
            case TAB_YESTERDAY:
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                return (int) (calendar.getTime().getTime() / 1000);
            default:
                break;
        }
        return OUT_OF_INDEX;
    }

    public static String convertTime(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sfd = new SimpleDateFormat("hh:mm:ss", Locale.US);
        return sfd.format(date);
    }

    public static String convertDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sfd = new SimpleDateFormat("dd:MM:yyyy", Locale.US);
        return sfd.format(date);
    }

    public static String getStatus(int status) {
        switch (status) {
            case BookingOder.STATUS_CANCELED:
                return Constant.BookingStatus.CANCEL;
            case BookingOder.STATUS_FINISHED:
                return Constant.BookingStatus.FINISHED;
            case BookingOder.STATUS_PENDING:
                return Constant.BookingStatus.PENDING;
            default:
                return Constant.BookingStatus.NA;
        }
    }
}
