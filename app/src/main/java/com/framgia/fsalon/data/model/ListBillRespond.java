package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by beepi on 10/08/2017.
 */
public class ListBillRespond {
    @SerializedName("date")
    @Expose
    private String mDate;
    @SerializedName("list_bill")
    @Expose
    private List<BillResponse> mListBill;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public List<BillResponse> getListBill() {
        return mListBill;
    }

    public void setListBill(List<BillResponse> listBill) {
        mListBill = listBill;
    }
}
