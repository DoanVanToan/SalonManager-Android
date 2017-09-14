package com.framgia.fsalon.data.model;

import android.os.Parcel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by beepi on 14/09/2017.
 */
public class ServiceBookingRespond extends BookingOder {
    protected ServiceBookingRespond(Parcel in) {
        super(in);
    }

    @SerializedName("get_order_items")
    @Expose
    private List<BillModel.Bill<BillItemRequest>> mOrderItems;

    public List<BillModel.Bill<BillItemRequest>> getOrderItems() {
        return mOrderItems;
    }

    public void setOrderItems(
        List<BillModel.Bill<BillItemRequest>> orderItems) {
        mOrderItems = orderItems;
    }
}
