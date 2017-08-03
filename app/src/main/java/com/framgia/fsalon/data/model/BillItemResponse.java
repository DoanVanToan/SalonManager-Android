package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by MyPC on 03/08/2017.
 */
public class BillItemResponse extends BillModel.BillItem {
    @Expose
    @SerializedName("bill_id")
    private int mBillId;
    @Expose
    @SerializedName("service_name")
    private String mServiceName;
    @Expose
    @SerializedName("discount")
    private float mDiscount;
    @Expose
    @SerializedName("created_at")
    private Date mCreatedAt;
    @Expose
    @SerializedName("updated_at")
    private Date mUpdatedAt;

    public int getBillId() {
        return mBillId;
    }

    public void setBillId(int billId) {
        mBillId = billId;
    }

    public String getServiceName() {
        return mServiceName;
    }

    public void setServiceName(String serviceName) {
        mServiceName = serviceName;
    }

    public float getDiscount() {
        return mDiscount;
    }

    public void setDiscount(float discount) {
        mDiscount = discount;
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
}
