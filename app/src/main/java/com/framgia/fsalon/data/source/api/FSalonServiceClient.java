package com.framgia.fsalon.data.source.api;

import android.app.Application;
import android.support.annotation.NonNull;

import framgia.retrofitservicecreator.api.service.ServiceClient;
import com.framgia.fsalon.utils.Constant;

public class FSalonServiceClient extends ServiceClient {
    private static FSalonApi sMFDMSApiInstance;

    public static void initialize(@NonNull Application application) {
        sMFDMSApiInstance = ServiceClient.createService(application, Constant.END_POINT_URL,
            FSalonApi.class);
    }

    public static FSalonApi getInstance() {
        if (sMFDMSApiInstance == null) {
            throw new RuntimeException("Need call method FSalonServiceClient#initialize() first");
        }
        return sMFDMSApiInstance;
    }
}
