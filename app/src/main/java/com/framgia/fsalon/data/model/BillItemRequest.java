package com.framgia.fsalon.data.model;

import com.google.gson.Gson;

/**
 * Created by MyPC on 02/08/2017.
 */
public class BillItemRequest extends BillModel.BillItem {
    private String mStylistName;
    private String mServiceName;

    public BillItemRequest(int id, int serviceProductId, int stylistId, float price, int qty,
                           float rowTotal,
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * Class Builder
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

        public BillItemRequest create() {
            return new BillItemRequest(mNestedId,
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
