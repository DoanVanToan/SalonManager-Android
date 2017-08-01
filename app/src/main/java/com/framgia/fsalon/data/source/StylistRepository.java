package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.Stylist;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by THM on 7/20/2017.
 */
public class StylistRepository implements StylistDataSource {
    private StylistDataSource mStylistRemoteDataSource;

    public StylistRepository(StylistDataSource stylistDataSource) {
        mStylistRemoteDataSource = stylistDataSource;
    }

    @Override
    public Observable<List<Stylist>> getAllStylists(int id) {
        return mStylistRemoteDataSource.getAllStylists(id);
    }
}
