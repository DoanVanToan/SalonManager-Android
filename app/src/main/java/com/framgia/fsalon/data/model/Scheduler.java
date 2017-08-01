package com.framgia.fsalon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fsalon.BR;

/**
 * Created by beepi on 28/07/2017.
 */
public class Scheduler extends BaseObservable {
    private String mNameCustomer;
    private boolean mStatus;
    private String mDate;

    public Scheduler(String nameCustomer, boolean status, String date) {
        mNameCustomer = nameCustomer;
        mStatus = status;
        mDate = date;
    }

    @Bindable
    public String getNameCustomer() {
        return mNameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        mNameCustomer = nameCustomer;
        notifyPropertyChanged(BR.nameCustomer);
    }

    @Bindable
    public boolean getStatus() {
        return mStatus;
    }

    public void setStatus(boolean status) {
        mStatus = status;
        notifyPropertyChanged(BR.status);
    }

    @Bindable
    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
        notifyPropertyChanged(BR.date);
    }
}
