package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by beepi on 14/09/2017.
 */
public class ServiceBooking {
    @SerializedName("order_booking_id")
    @Expose
    private int mOrderBookingId;
    @SerializedName("bill_items")
    @Expose
    private List<BillModel.BillItem> mBillItems;

    public int getOrderBookingId() {
        return mOrderBookingId;
    }

    public void setOrderBookingId(int orderBookingId) {
        mOrderBookingId = orderBookingId;
    }

    public List<BillModel.BillItem> getBillItems() {
        return mBillItems;
    }

    public void setBillItems(List<BillModel.BillItem> billItems) {
        mBillItems = billItems;
    }
}
