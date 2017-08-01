package com.framgia.fsalon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by MyPC on 27/07/2017.
 */
public class BookingCustomer extends BaseObservable {
    private String mName;
    private String mPhone;
    private String mAvatar;
    private int mIsVip;

    public BookingCustomer(String name, String phone) {
        mName = name;
        mPhone = phone;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    @Bindable
    public int getIsVip() {
        return mIsVip;
    }

    public void setIsVip(int isVip) {
        mIsVip = isVip;
        notifyPropertyChanged(BR.isVip);
    }
}
