package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.model.ManageBookingResponse;
import com.framgia.fsalon.data.source.ManageBookingDatasource;
import com.framgia.fsalon.data.source.api.FSalonApi;

import java.util.List;
import java.util.Map;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by beepi on 01/08/2017.
 */
public class ManageBookingRemoteDataSource extends BaseRemoteDataSource
    implements ManageBookingDatasource {
    public ManageBookingRemoteDataSource(FSalonApi api) {
        super(api);
    }

    @Override
    public Observable<List<ManageBookingResponse>> getListBooking() {
        return mFSalonApi.getManageBookings(createParams())
            .flatMap(
                new Function<Respone<ManageBookingResponse>,
                    ObservableSource<List<ManageBookingResponse>>>() {
                    @Override
                    public ObservableSource<List<ManageBookingResponse>> apply(
                        @NonNull Respone<ManageBookingResponse> manageBookingResponseRespone)
                        throws Exception {
                        return null;
                    }
                });
    }

    private Map<String, String> createParams() {
        return null;
    }
}
