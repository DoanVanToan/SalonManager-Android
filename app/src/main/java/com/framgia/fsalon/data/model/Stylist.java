package com.framgia.fsalon.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by THM on 7/20/2017.
 */
public class Stylist implements Parcelable{
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("name")
    private String mName;
    @Expose
    @SerializedName("email")
    private String mEmail;
    @Expose
    @SerializedName("phone")
    private String mPhone;
    @Expose
    @SerializedName("birthday")
    private String mBirthDay;
    @Expose
    @SerializedName("avatar")
    private String mAvatar;
    @Expose
    @SerializedName("gender")
    private String mGender;
    @Expose
    @SerializedName("permission")
    private int mPermission;
    @Expose
    @SerializedName("experience")
    private String mExperience;
    @Expose
    @SerializedName("specialize")
    private String mSpecialize;
    @Expose
    @SerializedName("about_me")
    private String mAboutMe;
    @Expose
    @SerializedName("department_id")
    private int mDepartmentId;
    @Expose
    @SerializedName("created_at")
    private String mCreateAt;
    @Expose
    @SerializedName("updated_at")
    private String mUpdatedAt;

    public Stylist(String name) {
        mName = name;
    }

    protected Stylist(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mEmail = in.readString();
        mPhone = in.readString();
        mBirthDay = in.readString();
        mAvatar = in.readString();
        mGender = in.readString();
        mPermission = in.readInt();
        mExperience = in.readString();
        mSpecialize = in.readString();
        mAboutMe = in.readString();
        mDepartmentId = in.readInt();
        mCreateAt = in.readString();
        mUpdatedAt = in.readString();
    }

    public static final Creator<Stylist> CREATOR = new Creator<Stylist>() {
        @Override
        public Stylist createFromParcel(Parcel in) {
            return new Stylist(in);
        }

        @Override
        public Stylist[] newArray(int size) {
            return new Stylist[size];
        }
    };

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

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getBirthDay() {
        return mBirthDay;
    }

    public void setBirthDay(String birthDay) {
        mBirthDay = birthDay;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
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

    public String getAboutMe() {
        return mAboutMe;
    }

    public void setAboutMe(String aboutMe) {
        mAboutMe = aboutMe;
    }

    public int getDepartmentId() {
        return mDepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        mDepartmentId = departmentId;
    }

    public String getCreateAt() {
        return mCreateAt;
    }

    public void setCreateAt(String createAt) {
        mCreateAt = createAt;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mEmail);
        dest.writeString(mPhone);
        dest.writeString(mBirthDay);
        dest.writeString(mAvatar);
        dest.writeString(mGender);
        dest.writeInt(mPermission);
        dest.writeString(mExperience);
        dest.writeString(mSpecialize);
        dest.writeString(mAboutMe);
        dest.writeInt(mDepartmentId);
        dest.writeString(mCreateAt);
        dest.writeString(mUpdatedAt);
    }
}
