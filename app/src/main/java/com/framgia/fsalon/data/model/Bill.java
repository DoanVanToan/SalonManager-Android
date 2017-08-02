package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MyPC on 02/08/2017.
 */
public class Bill {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("service_product_id")
    private int mServiceProductId;
    @Expose
    @SerializedName("stylist_id")
    private int mStylistId;
    @Expose
    @SerializedName("price")
    private float mPrice;
    @Expose
    @SerializedName("qty")
    private int mQty;
    @Expose
    @SerializedName("row_total")
    private float mRowTotal;
    private String mSlylistName;
    private String mServiceName;

    public Bill(int serviceProductId, int stylistId, float price, int qty,
                String slylistName, String serviceName) {
        mServiceProductId = serviceProductId;
        mStylistId = stylistId;
        mPrice = price;
        mQty = qty;
        mSlylistName = slylistName;
        mServiceName = serviceName;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getServiceProductId() {
        return mServiceProductId;
    }

    public void setServiceProductId(int serviceProductId) {
        mServiceProductId = serviceProductId;
    }

    public int getStylistId() {
        return mStylistId;
    }

    public void setStylistId(int stylistId) {
        mStylistId = stylistId;
    }

    public float getPrice() {
        return mPrice;
    }

    public void setPrice(float price) {
        mPrice = price;
    }

    public int getQty() {
        return mQty;
    }

    public void setQty(int qty) {
        mQty = qty;
    }

    public float getRowTotal() {
        return mRowTotal;
    }

    public void setRowTotal(float rowTotal) {
        mRowTotal = rowTotal;
    }

    public String getSlylistName() {
        return mSlylistName;
    }

    public void setSlylistName(String slylistName) {
        mSlylistName = slylistName;
    }

    public String getServiceName() {
        return mServiceName;
    }

    public void setServiceName(String serviceName) {
        mServiceName = serviceName;
    }

    public float getRowToalBill() {
        mRowTotal = mPrice * mQty;
        return mRowTotal;
    }
}
