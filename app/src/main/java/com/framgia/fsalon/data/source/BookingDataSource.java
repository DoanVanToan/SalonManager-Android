package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingResponse;

import io.reactivex.Observable;

/**
 * Created by framgia on 7/21/17.
 */

public interface BookingDataSource {

    Observable<BookingResponse> getBookings(int salonId, long time, int stylelistId);

    Observable<BookingResponse> getBookings(int salonId, long time);

    Observable<BookingOder> book(String phone, String name, int renderBookingId, int stylistId);
}
