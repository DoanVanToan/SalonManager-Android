package com.framgia.fsalon.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MyPC on 20/07/2017.
 */
public class Token {
    @Expose
    @SerializedName("token_type")
    private String mTokenType;
    @Expose
    @SerializedName("expires_in")
    private int mExpiresIn;
    @Expose
    @SerializedName("access_token")
    private String mAccessToken;
    @Expose
    @SerializedName("refresh_token")
    private String mRefreshToken;

    public String getTokenType() {
        return mTokenType;
    }

    public void setTokenType(String tokenType) {
        mTokenType = tokenType;
    }

    public int getExpiresIn() {
        return mExpiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        mExpiresIn = expiresIn;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getRefreshToken() {
        return mRefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        mRefreshToken = refreshToken;
    }
}
