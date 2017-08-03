package com.framgia.fsalon.data.model;

import android.databinding.Bindable;

import com.framgia.fsalon.BR;

import java.util.List;

/**
 * Created by MyPC on 02/08/2017.
 */
public class BillRequest extends BillModel.Bill<BillItemRequest> {
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
    public List<BillItemRequest> getBillItems() {
        return mBillItems;
    }

    public void setBillItems(List<BillItemRequest> billItems) {
        mBillItems = billItems;
        notifyPropertyChanged(BR.billItems);
    }
}
