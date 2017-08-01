package com.framgia.fsalon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fsalon.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MyPC on 27/07/2017.
 */
public class BookingCustomer extends BaseObservable {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("email")
    @Expose
    private String mEmail;
    @SerializedName("birthday")
    @Expose
    private String mBirthday;
    @SerializedName("gender")
    @Expose
    private String mGender;
    @SerializedName("permission")
    @Expose
    private int mPermission;
    @SerializedName("experience")
    @Expose
    private String mExperience;
    @SerializedName("specialize")
    @Expose
    private String mSpecialize;
    @SerializedName("created_at")
    @Expose
    private String mCreateAt;
    @SerializedName("updated_at")
    @Expose
    private String mUpdateAt;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("phone")
    @Expose
    private String mPhone;
    @SerializedName("avatar")
    @Expose
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

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String birthday) {
        mBirthday = birthday;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public int getPermission() {
        return mPermission;
    }

    public void setPermission(int permission) {
        mPermission = permission;
    }

    public String getExperience() {
        return mExperience;
    }

    public void setExperience(String experience) {
        mExperience = experience;
    }

    public String getSpecialize() {
        return mSpecialize;
    }

    public void setSpecialize(String specialize) {
        mSpecialize = specialize;
    }

    public String getCreateAt() {
        return mCreateAt;
    }

    public void setCreateAt(String createAt) {
        mCreateAt = createAt;
    }

    public String getUpdateAt() {
        return mUpdateAt;
    }

    public void setUpdateAt(String updateAt) {
        mUpdateAt = updateAt;
    }
}
