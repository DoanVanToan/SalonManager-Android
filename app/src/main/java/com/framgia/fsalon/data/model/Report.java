package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by THM on 8/25/2017.
 */
public class Report {
    @Expose
    @SerializedName("label")
    private String mLabel;
    @Expose
    @SerializedName("value")
    private float mValue;
    @Expose
    @SerializedName("customer_new")
    private int mNewCustomer;
    @Expose
    @SerializedName("customer_old")
    private int mOldCustomer;

    public int getNewCustomer() {
        return mNewCustomer;
    }

    public void setNewCustomer(int newCustomer) {
        mNewCustomer = newCustomer;
    }

    public int getOldCustomer() {
        return mOldCustomer;
    }

    public void setOldCustomer(int oldCustomer) {
        mOldCustomer = oldCustomer;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public float getValue() {
        return mValue;
    }

    public void setValue(float value) {
        mValue = value;
    }
}
