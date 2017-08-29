package com.framgia.fsalon.data.model;

import java.util.ArrayList;
import java.util.List;

import static com.framgia.fsalon.data.model.BookingOder.CANCELED;
import static com.framgia.fsalon.data.model.BookingOder.IN_PROGRESS;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_CANCELED;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_IN_LATE;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_IN_PROGRESS;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_WATTING;

/**
 * Created by MyPC on 29/08/2017.
 */
public class Status {
    private static final String NO_STATUS = "No Status";
    private int mId;
    private String mName;

    public Status(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return mName != null ? mName : NO_STATUS;
    }

    public static List<Status> getStatuses(int statusIdCurrent) {
        List<Status> statuses = new ArrayList<>();
        switch (statusIdCurrent) {
            case STATUS_WATTING:
                statuses.add(new Status(STATUS_IN_PROGRESS, IN_PROGRESS));
                statuses.add(new Status(STATUS_CANCELED, CANCELED));
                break;
            case STATUS_IN_LATE:
                statuses.add(new Status(STATUS_CANCELED, CANCELED));
                break;
            case STATUS_IN_PROGRESS:
                statuses.add(new Status(STATUS_CANCELED, CANCELED));
                break;
            default:
                break;
        }
        return statuses;
    }
}
