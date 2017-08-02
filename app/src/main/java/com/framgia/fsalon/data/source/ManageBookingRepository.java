package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.ManageBookingResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by beepi on 01/08/2017.
 */
public class ManageBookingRepository implements ManageBookingDatasource {
    private ManageBookingDatasource mDatasource;

    public ManageBookingRepository(ManageBookingDatasource datasource) {
        mDatasource = datasource;
    }

    @Override
    public Observable<List<ManageBookingResponse>> getListBooking(String filterChoice, int page,
                                                                  int perpage, int status,
                                                                  int startDate, int endDate) {
        return mDatasource.getListBooking(filterChoice, page, perpage, status, startDate,
            endDate);
    }
}
