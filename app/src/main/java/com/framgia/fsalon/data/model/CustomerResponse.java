package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by huynh on 10-Aug-17.
 */
public class CustomerResponse {
    @SerializedName("data")
    @Expose
    private List<Customer> mData;

    public List<Customer> getData() {
        return mData;
    }

    public void setData(List<Customer> data) {
        mData = data;
    }
}
