package com.framgia.fsalon.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 * Created by MyPC on 02/08/2017.
 */
public class BillItemRequest extends BillModel.BillItem implements Parcelable {
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

    protected BillItemRequest(Parcel in) {
        mStylistName = in.readString();
        mServiceName = in.readString();
    }

    public static final Creator<BillItemRequest> CREATOR = new Creator<BillItemRequest>() {
        @Override
        public BillItemRequest createFromParcel(Parcel in) {
            return new BillItemRequest(in);
        }

        @Override
        public BillItemRequest[] newArray(int size) {
            return new BillItemRequest[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mServiceProductId);
        dest.writeInt(mStylistId);
        dest.writeFloat(mPrice);
        dest.writeInt(mQty);
        dest.writeFloat(mRowTotal);
        dest.writeString(mStylistName);
        dest.writeString(mServiceName);
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
