package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by framgia on 7/21/17.
 */

public class BookingResponse {

    @SerializedName("status")
    @Expose
    private String mStatus;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("renders")
    @Expose
    private List<BookingRender> mRenders;

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public List<BookingRender> getRenders() {
        return mRenders;
    }

    public void setRenders(List<BookingRender> renders) {
        mRenders = renders;
    }
}
