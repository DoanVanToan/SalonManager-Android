package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingResponse;
import com.framgia.fsalon.data.source.BookingDataSource;
import com.framgia.fsalon.data.source.api.FSalonApi;
import com.framgia.fsalon.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

import static com.framgia.fsalon.utils.Constant.ApiParram.DATE;
import static com.framgia.fsalon.utils.Constant.ApiParram.DEPARTMENT_ID;
import static com.framgia.fsalon.utils.Constant.ApiParram.NAME;
import static com.framgia.fsalon.utils.Constant.ApiParram.PHONE;
import static com.framgia.fsalon.utils.Constant.ApiParram.RENDER_BOOKING_ID;
import static com.framgia.fsalon.utils.Constant.ApiParram.STYLIST_CHOSEN;
import static com.framgia.fsalon.utils.Constant.ApiParram.STYLIST_ID;

/**
 * Created by framgia on 7/21/17.
 */
public class BookingRemoteDataSource extends BaseRemoteDataSource implements BookingDataSource {
    public BookingRemoteDataSource(FSalonApi FSalonApi) {
        super(FSalonApi);
    }

    @Override
    public Observable<BookingResponse> getBookings(int salonId, long time, int stylelistId) {
        Map<String, String> parram = new HashMap<>();
        parram.put(DEPARTMENT_ID, String.valueOf(salonId));
        parram.put(DATE, String.valueOf(time));
        if (stylelistId != -1) {
            parram.put(STYLIST_ID, String.valueOf(stylelistId));
        }
        return mFSalonApi.getBookings(parram)
            .flatMap(new Function<Respone<BookingResponse>, ObservableSource<BookingResponse>>() {
                @Override
                public ObservableSource<BookingResponse> apply(
                    @NonNull Respone<BookingResponse> bookingResponseRespone)
                    throws Exception {
                    return Utils.getResponse(bookingResponseRespone);
                }
            });
    }

    @Override
    public Observable<BookingResponse> getBookings(int salonId, long time) {
        return getBookings(salonId, time, -1);
    }

    @Override
    public Observable<BookingOder> book(String phone, String name, int renderBookingId,
                                        int stylistId) {
        Map<String, String> parram = new HashMap<>();
        parram.put(PHONE, phone);
        parram.put(NAME, name);
        parram.put(RENDER_BOOKING_ID, String.valueOf(renderBookingId));
        parram.put(STYLIST_CHOSEN, String.valueOf(stylistId));
        return mFSalonApi.book(parram)
            .flatMap(new Function<Respone<BookingOder>, ObservableSource<BookingOder>>() {
                @Override
                public ObservableSource<BookingOder> apply(
                    @NonNull Respone<BookingOder> bookingOderRespone)
                    throws Exception {
                    return Utils.getResponse(bookingOderRespone);
                }
            });
    }
}
