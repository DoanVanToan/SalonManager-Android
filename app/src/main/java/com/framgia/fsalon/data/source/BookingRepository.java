package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingResponse;

import io.reactivex.Observable;

/**
 * Created by framgia on 7/21/17.
 */

public class BookingRepository implements BookingDataSource {

    private BookingDataSource mRemoteDataSource;

    public BookingRepository(BookingDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<BookingResponse> getBookings(int salonId, long time, int stylelistId) {
        return mRemoteDataSource.getBookings(salonId, time, stylelistId);
    }

    @Override
    public Observable<BookingResponse> getBookings(int salonId, long time) {
        return mRemoteDataSource.getBookings(salonId, time);
    }

    @Override
    public Observable<BookingOder> book(String phone, String name, int renderBookingId,
                                        int stylistId) {
        return mRemoteDataSource.book(phone, name, renderBookingId, stylistId);
    }
}
