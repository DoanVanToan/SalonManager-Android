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
    public Observable<List<ManageBookingResponse>> getListBooking(String filterChoice,
                                                                  String status, int startDate,
                                                                  int endDate, int departmentId) {
        return mDatasource.getListBooking(filterChoice, status, startDate, endDate, departmentId);
    }
}
