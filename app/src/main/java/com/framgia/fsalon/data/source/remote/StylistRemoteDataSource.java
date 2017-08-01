package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.data.source.StylistDataSource;
import com.framgia.fsalon.data.source.api.FSalonApi;
import com.framgia.fsalon.utils.Utils;

import java.util.List;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by THM on 7/20/2017.
 */
public class StylistRemoteDataSource extends BaseRemoteDataSource implements StylistDataSource {
    public StylistRemoteDataSource(FSalonApi FSalonApi) {
        super(FSalonApi);
    }

    @Override
    public Observable<List<Stylist>> getAllStylists(int id) {
        return mFSalonApi.getStylistBySalonId(id).
            flatMap(new Function<Respone<List<Stylist>>, ObservableSource<List<Stylist>>>() {
                @Override
                public ObservableSource<List<Stylist>> apply(
                    @NonNull Respone<List<Stylist>> listRespone)
                    throws Exception {
                    return Utils.getResponse(listRespone);
                }
            });
    }
}
