package com.framgia.fsalon.data.source;

import android.support.annotation.NonNull;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingResponse;

import java.io.File;
import java.util.List;

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

    @Override
    public Observable<BookingOder> getBookingByPhone(String phone) {
        return mRemoteDataSource.getBookingByPhone(phone);
    }

    @Override
    public Observable<BookingOder> getBookingById(int id) {
        return mRemoteDataSource.getBookingById(id);
    }

    @Override
    public Observable<List<String>> changeStatusBooking(int id, int statusId) {
        return mRemoteDataSource.changeStatusBooking(id, statusId);
    }

    @Override
    public Observable<BookingOder> postImageByStylist(@NonNull int orderBookingId,
                                                      @NonNull String imagePaths) {
        return mRemoteDataSource.postImageByStylist(orderBookingId, imagePaths);
    }

    @Override
    public Observable<List<String>> postMultiImages(@NonNull List<File> files, String mediaType,
                                                    String folder) {
        return mRemoteDataSource.postMultiImages(files, mediaType, folder);
    }
}
