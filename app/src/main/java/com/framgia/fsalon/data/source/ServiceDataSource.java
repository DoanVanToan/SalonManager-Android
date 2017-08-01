package com.framgia.fsalon.data.source;

import android.app.Service;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by THM on 8/1/2017.
 */
public interface ServiceDataSource {
    Observable<List<Service>> getAllServices();
}
