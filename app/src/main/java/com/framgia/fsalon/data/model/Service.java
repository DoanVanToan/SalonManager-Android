package com.framgia.fsalon.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by THM on 8/1/2017.
 */
public class Service implements Parcelable {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("short_description")
    @Expose
    private String mShortDescription;
    @SerializedName("description")
    @Expose
    private String mDescription;
    @SerializedName("price")
    @Expose
    private int mPrice;
    @SerializedName("avg_rate")
    @Expose
    private double mAvgRate;
    @SerializedName("total_rate")
    @Expose
    private int mTotalRate;
    @SerializedName("created_at")
    @Expose
    private Date mCreatedAt;
    @SerializedName("updated_at")
    @Expose
    private Date mUpdatedAt;

    public Service(String name) {
        mName = name;
    }

    public Service(int id) {
        mId = id;
    }

    protected Service(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mShortDescription = in.readString();
        mDescription = in.readString();
        mPrice = in.readInt();
        mAvgRate = in.readDouble();
        mTotalRate = in.readInt();
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
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

    public String getShortDescription() {
        return mShortDescription;
    }

    public void setShortDescription(String shortDescription) {
        mShortDescription = shortDescription;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    public double getAvgRate() {
        return mAvgRate;
    }

    public void setAvgRate(double avgRate) {
        mAvgRate = avgRate;
    }

    public int getTotalRate() {
        return mTotalRate;
    }

    public void setTotalRate(int totalRate) {
        mTotalRate = totalRate;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }

    public Date getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        mUpdatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mShortDescription);
        dest.writeString(mDescription);
        dest.writeInt(mPrice);
        dest.writeDouble(mAvgRate);
        dest.writeInt(mTotalRate);
    }

    @Override
    public String toString() {
        return mName;
    }
}
