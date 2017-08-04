package com.framgia.fsalon.data.source.api;

import com.framgia.fsalon.FSalonApplication;
import com.framgia.fsalon.data.model.UserRespone;
import com.framgia.fsalon.data.source.local.sharepref.SharePreferenceImp;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.framgia.fsalon.data.source.local.sharepref.SharePreferenceKey.PREF_USER;

/**
 * Created by MyPC on 03/08/2017.
 */
public class FSalonInterceptor implements Interceptor {
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_AUTHORIZE = "Authorization";
    public static final String HEADER_VALUE_ACCEPT = "application/json";

    public FSalonInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder().header(HEADER_ACCEPT, HEADER_VALUE_ACCEPT);
        String token = getToken();
        if (token != null) {
            builder.header(HEADER_AUTHORIZE, token);
        }
        return chain.proceed(builder.build());
    }

    public static String getToken() {
        SharePreferenceImp sharePreferenceImp =
            new SharePreferenceImp(FSalonApplication.getInstant());
        String json = sharePreferenceImp.get(PREF_USER, String.class);
        UserRespone user = new Gson().fromJson(json, UserRespone.class);
        return user != null
            ? user.getToken().getTokenType() + " " + user.getToken().getAccessToken() : null;
    }
}
