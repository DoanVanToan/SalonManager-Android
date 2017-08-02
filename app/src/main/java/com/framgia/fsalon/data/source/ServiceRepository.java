package com.framgia.fsalon.data.source;

import android.app.Service;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by MyPC on 02/08/2017.
 */
public class ServiceRepository implements ServiceDataSource {
    private ServiceDataSource mRemoteDatSource;

    public ServiceRepository(ServiceDataSource remoteDatSource) {
        mRemoteDatSource = remoteDatSource;
    }

    @Override
    public Observable<List<Service>> getAllServices() {
        return mRemoteDatSource.getAllServices();
    }
}
