package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.Salon;
import java.util.List;
import io.reactivex.Observable;

/**
 * Created by framgia on 7/20/17.
 */

public class SalonRepository implements SalonDataSource {

    private SalonDataSource mRemoteDatSource;

    public SalonRepository(SalonDataSource remoteDatSource) {
        mRemoteDatSource = remoteDatSource;
    }

    @Override
    public Observable<List<Salon>> getAllSalons() {
        return mRemoteDatSource.getAllSalons();
    }
}
