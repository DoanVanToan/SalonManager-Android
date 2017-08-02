package com.framgia.fsalon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fsalon.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MyPC on 02/08/2017.
 */
public class BillRequest extends BaseObservable{
    @Expose
    @SerializedName("customer_id")
    private int mCustomerId;
    @Expose
    @SerializedName("phone")
    private String mPhone;
    @Expose
    @SerializedName("status")
    private int mStatus;
    @Expose
    @SerializedName("customer_name")
    private String mCustomerName;
    @Expose
    @SerializedName("order_booking_id")
    private int mOrderBookingId;
    @Expose
    @SerializedName("grand_total")
    private float mGrandTotal;
    @Expose
    @SerializedName("bill_items")
    private List<Bill> mBillItems;

    @Bindable
    public int getCustomerId() {
        return mCustomerId;
    }

    public void setCustomerId(int customerId) {
        mCustomerId = customerId;
        notifyPropertyChanged(BR.customerId);
    }

    @Bindable
    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
        notifyPropertyChanged(BR.status);
    }

    @Bindable
    public String getCustomerName() {
        return mCustomerName;
    }

    public void setCustomerName(String customerName) {
        mCustomerName = customerName;
        notifyPropertyChanged(BR.customerName);
    }

    @Bindable
    public int getOrderBookingId() {
        return mOrderBookingId;
    }

    public void setOrderBookingId(int orderBookingId) {
        mOrderBookingId = orderBookingId;
        notifyPropertyChanged(BR.orderBookingId);
    }

    @Bindable
    public float getGrandTotal() {
        return mGrandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        mGrandTotal = grandTotal;
        notifyPropertyChanged(BR.grandTotal);
    }

    @Bindable
    public List<Bill> getBillItems() {
        return mBillItems;
    }

    public void setBillItems(List<Bill> billItems) {
        mBillItems = billItems;
        notifyPropertyChanged(BR.billItems);
    }
}
