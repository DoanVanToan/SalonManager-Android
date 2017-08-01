package com.framgia.fsalon;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import io.fabric.sdk.android.Fabric;

/**
 * Created by MyPC on 20/07/2017.
 */
public class FSalonApplication extends Application {

    private static FSalonApplication sInstant;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        FSalonServiceClient.initialize(this);
        sInstant = this;
    }

    public static FSalonApplication getInstant() {
        return sInstant;
    }
}
