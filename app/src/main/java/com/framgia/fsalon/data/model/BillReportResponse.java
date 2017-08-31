package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by THM on 8/28/2017.
 */
public class BillReportResponse extends ReportResponse {
    @Expose
    @SerializedName("total_sale")
    private int mTotalSale;

    public int getTotalSale() {
        return mTotalSale;
    }

    public void setTotalSale(int totalSale) {
        mTotalSale = totalSale;
    }
}
