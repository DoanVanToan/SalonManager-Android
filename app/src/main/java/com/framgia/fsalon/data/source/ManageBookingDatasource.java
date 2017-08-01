package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.ManageBookingResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by beepi on 01/08/2017.
 */
public interface ManageBookingDatasource {
    Observable<List<ManageBookingResponse>> getListBooking(String filterChoice, int page,
                                                           int perpage,
                                                           String status, String startDate,
                                                           String endDate,
                                                           String monthInput,
                                                           String weekInput);
}
