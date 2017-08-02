package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by beepi on 01/08/2017.
 */
public class ManageBookingResponse {
    @SerializedName("date_book")
    @Expose
    private String mDateBook;
    @SerializedName("list_book")
    @Expose
    private List<BookingOder> mListBook;

    public String getDateBook() {
        return mDateBook;
    }

    public void setDateBook(String dateBook) {
        mDateBook = dateBook;
    }

    public List<BookingOder> getListBook() {
        return mListBook;
    }

    public void setListBook(List<BookingOder> listBook) {
        mListBook = listBook;
    }
}
