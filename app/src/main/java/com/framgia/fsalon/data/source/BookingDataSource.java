package com.framgia.fsalon.data.source;

import android.support.annotation.NonNull;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingResponse;
import com.framgia.fsalon.data.model.ImageResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by framgia on 7/21/17.
 */
public interface BookingDataSource {
    Observable<BookingResponse> getBookings(int salonId, long time, int stylelistId);
    Observable<BookingResponse> getBookings(int salonId, long time);
    Observable<BookingOder> book(String phone, String name, int renderBookingId, int stylistId);
    Observable<BookingOder> getBookingByPhone(String phone);
    Observable<BookingOder> getBookingById(int id);
    Observable<List<String>> changeStatusBooking(int id, int statusId);
    Observable<BookingOder> postImageByStylist(@NonNull int orderBookingId,
                                               @NonNull ImageResponse image);
}
