package com.framgia.fsalon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fsalon.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by beepi on 01/08/2017.
 */
public class ManageBookingResponse extends BaseObservable {
    @SerializedName("status")
    @Expose
    private String mStatus;
    @SerializedName("date_book")
    @Expose
    private String mDateBook;
    @SerializedName("time_book")
    @Expose
    private String mTimeBook;
    @SerializedName("customer")
    @Expose
    private BookingCustomer mCustomer;
    @SerializedName("stylist")
    @Expose
    private Stylist mStylist;

    @Bindable
    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
        notifyPropertyChanged(BR.status);
    }

    @Bindable
    public String getDateBook() {
        return mDateBook;
    }

    public void setDateBook(String dateBook) {
        mDateBook = dateBook;
        notifyPropertyChanged(BR.dateBook);
    }

    @Bindable
    public String getTimeBook() {
        return mTimeBook;
    }

    public void setTimeBook(String timeBook) {
        mTimeBook = timeBook;
        notifyPropertyChanged(BR.timeBook);
    }

    @Bindable
    public BookingCustomer getCustomer() {
        return mCustomer;
    }

    public void setCustomer(BookingCustomer customer) {
        mCustomer = customer;
        notifyPropertyChanged(BR.customer);
    }

    @Bindable
    public Stylist getStylist() {
        return mStylist;
    }

    public void setStylist(Stylist stylist) {
        mStylist = stylist;
        notifyPropertyChanged(BR.stylist);
    }
}
