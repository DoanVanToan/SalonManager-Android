package com.framgia.fsalon.data.model;

import com.framgia.fsalon.data.model.Token;
import com.framgia.fsalon.data.model.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MyPC on 20/07/2017.
 */
public class UserRespone {
    @Expose
    @SerializedName("user")
    private User user;
    @Expose
    @SerializedName("token")
    private Token token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
