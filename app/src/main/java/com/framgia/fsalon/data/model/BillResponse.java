package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by MyPC on 03/08/2017.
 */
public class BillResponse extends BillModel.Bill<BillItemResponse> {
    @Expose
    @SerializedName("service_total")
    private int mServiceTotal;
    @Expose
    @SerializedName("created_at")
    private Date mCreatedAt;
    @Expose
    @SerializedName("updated_at")
    private Date mUpdatedAt;
    @Expose
    @SerializedName("department")
    private Salon mDepartment;
    @Expose
    @SerializedName("bookings")
    private BookingOder mBookingOder;
    private String mStatusName;
    private int mStatusColor;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getServiceTotal() {
        return mServiceTotal;
    }

    public void setServiceTotal(int serviceTotal) {
        mServiceTotal = serviceTotal;
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

    public Salon getDepartment() {
        return mDepartment;
    }

    public void setDepartment(Salon department) {
        mDepartment = department;
    }

    public BookingOder getBookingOder() {
        return mBookingOder;
    }

    public void setBookingOder(BookingOder bookingOder) {
        mBookingOder = bookingOder;
    }

    public String getStatusName() {
        return mStatusName;
    }

    public void setStatusName(String statusName) {
        mStatusName = statusName;
    }

    public int getStatusColor() {
        return mStatusColor;
    }

    public void setStatusColor(int statusColor) {
        mStatusColor = statusColor;
    }
}
