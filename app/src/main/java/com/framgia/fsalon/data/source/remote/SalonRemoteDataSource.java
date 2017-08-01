package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.model.Salon;
import com.framgia.fsalon.data.source.SalonDataSource;
import com.framgia.fsalon.data.source.api.FSalonApi;
import com.framgia.fsalon.utils.Utils;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

import java.util.List;

/**
 * Created by framgia on 7/20/17.
 */
public class SalonRemoteDataSource extends BaseRemoteDataSource implements SalonDataSource {
    public SalonRemoteDataSource(FSalonApi FSalonApi) {
        super(FSalonApi);
    }

    @Override
    public Observable<List<Salon>> getAllSalons() {
        return mFSalonApi.getSalon()
            .flatMap(new Function<Respone<List<Salon>>, ObservableSource<List<Salon>>>() {
                @Override
                public ObservableSource<List<Salon>> apply(
                    @NonNull Respone<List<Salon>> listRespone)
                    throws Exception {
                    return Utils.getResponse(listRespone);
                }
            });
    }
}
