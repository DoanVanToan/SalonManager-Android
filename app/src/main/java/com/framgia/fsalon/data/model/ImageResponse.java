package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by MyPC on 24/08/2017.
 */
public class ImageResponse {
    @Expose
    @SerializedName("id")
    private int mId;
    @Expose
    @SerializedName("path_origin")
    private String mPathOrigin;
    @Expose
    @SerializedName("path_thumb")
    private String mPathThumb;
    @Expose
    @SerializedName("media_table_id")
    private int mMediaTableId;
    @Expose
    @SerializedName("media_table_type")
    private String mMediaTableType;
    @Expose
    @SerializedName("created_at")
    private Date mCreatedAt;
    @Expose
    @SerializedName("updated_at")
    private Date mUpdatedAt;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getPathOrigin() {
        return mPathOrigin;
    }

    public void setPathOrigin(String pathOrigin) {
        mPathOrigin = pathOrigin;
    }

    public String getPathThumb() {
        return mPathThumb;
    }

    public void setPathThumb(String pathThumb) {
        mPathThumb = pathThumb;
    }

    public int getMediaTableId() {
        return mMediaTableId;
    }

    public void setMediaTableId(int mediaTableId) {
        mMediaTableId = mediaTableId;
    }

    public String getMediaTableType() {
        return mMediaTableType;
    }

    public void setMediaTableType(String mediaTableType) {
        mMediaTableType = mediaTableType;
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
}
