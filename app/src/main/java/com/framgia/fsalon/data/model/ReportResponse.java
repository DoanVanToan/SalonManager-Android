package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by THM on 8/25/2017.
 */
public class ReportResponse {
    @Expose
    @SerializedName("statistical")
    protected List<Report> mData;

    public List<Report> getData() {
        return mData;
    }

    public void setData(List<Report> data) {
        mData = data;
    }
}
