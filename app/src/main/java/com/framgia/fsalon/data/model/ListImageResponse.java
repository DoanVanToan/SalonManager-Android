package com.framgia.fsalon.data.model;

import java.util.List;

/**
 * Created by MyPC on 23/08/2017.
 */
public class ListImageResponse {
    private String mDate;
    private List<String> mImages;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void setImages(List<String> images) {
        mImages = images;
    }
}
