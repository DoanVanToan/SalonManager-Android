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
    private String mStylistName;
    private String mServiceName;

    public Bill(int id, int serviceProductId, int stylistId, float price, int qty, float rowTotal,
                String stylistName, String serviceName) {
        mId = id;
        mServiceProductId = serviceProductId;
        mStylistId = stylistId;
        mPrice = price;
        mQty = qty;
        mRowTotal = rowTotal;
        mStylistName = stylistName;
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

    public String getStylistName() {
        return mStylistName;
    }

    public void setStylistName(String stylistName) {
        mStylistName = stylistName;
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

    /**
     *  Class Builder
     */
    public static class Builder {
        private int mNestedId;
        private int mNestedServiceProductId;
        private int mNestedStylistId;
        private float mNestedPrice;
        private int mNestedQty;
        private float mNestedRowTotal;
        private String mNestedStylistName;
        private String mNestedServiceName;

        public Builder setId(int nestedId) {
            mNestedId = nestedId;
            return this;
        }

        public Builder setServiceProductId(int nestedServiceProductId) {
            mNestedServiceProductId = nestedServiceProductId;
            return this;
        }

        public Builder setStylistId(int nestedStylistId) {
            mNestedStylistId = nestedStylistId;
            return this;
        }

        public Builder setPrice(float nestedPrice) {
            mNestedPrice = nestedPrice;
            return this;
        }

        public Builder setQty(int nestedQty) {
            mNestedQty = nestedQty;
            return this;
        }

        public Builder setRowTotal(float nestedRowTotal) {
            mNestedRowTotal = nestedRowTotal;
            return this;
        }

        public Builder setStylistName(String nestedSlylistName) {
            mNestedStylistName = nestedSlylistName;
            return this;
        }

        public Builder setServiceName(String nestedServiceName) {
            mNestedServiceName = nestedServiceName;
            return this;
        }

        public Bill create() {
            return new Bill(mNestedId,
                mNestedServiceProductId,
                mNestedStylistId,
                mNestedPrice,
                mNestedQty,
                mNestedRowTotal,
                mNestedStylistName,
                mNestedServiceName);
        }
    }
}
