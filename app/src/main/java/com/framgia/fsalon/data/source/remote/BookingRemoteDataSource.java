package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingResponse;
import com.framgia.fsalon.data.source.BookingDataSource;
import com.framgia.fsalon.data.source.api.FSalonApi;
import com.framgia.fsalon.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.framgia.fsalon.utils.Constant.ApiParram.DATE;
import static com.framgia.fsalon.utils.Constant.ApiParram.DEPARTMENT_ID;
import static com.framgia.fsalon.utils.Constant.ApiParram.IMAGE;
import static com.framgia.fsalon.utils.Constant.ApiParram.NAME;
import static com.framgia.fsalon.utils.Constant.ApiParram.ORDER_BOOKING_ID;
import static com.framgia.fsalon.utils.Constant.ApiParram.PHONE;
import static com.framgia.fsalon.utils.Constant.ApiParram.POST_IMAGE;
import static com.framgia.fsalon.utils.Constant.ApiParram.RENDER_BOOKING_ID;
import static com.framgia.fsalon.utils.Constant.ApiParram.STYLIST_CHOSEN;
import static com.framgia.fsalon.utils.Constant.ApiParram.STYLIST_ID;
import static com.framgia.fsalon.utils.Constant.OUT_OF_INDEX;

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
        if (stylistId != -1) {
            parram.put(STYLIST_CHOSEN, String.valueOf(stylistId));
        }
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

    @Override
    public Observable<BookingOder> getBookingByPhone(String phone) {
        return mFSalonApi.getBookingByPhone(phone)
            .flatMap(new Function<Respone<BookingOder>, ObservableSource<BookingOder>>() {
                @Override
                public ObservableSource<BookingOder> apply(
                    @NonNull Respone<BookingOder> bookingOderRespone)
                    throws Exception {
                    return Utils.getResponse(bookingOderRespone);
                }
            });
    }

    @Override
    public Observable<BookingOder> getBookingById(int id) {
        return mFSalonApi.getBookingById(id)
            .flatMap(new Function<Respone<BookingOder>, ObservableSource<BookingOder>>() {
                @Override
                public ObservableSource<BookingOder> apply(
                    @NonNull Respone<BookingOder> bookingOderRespone)
                    throws Exception {
                    return Utils.getResponse(bookingOderRespone);
                }
            });
    }

    @Override
    public Observable<List<String>> changeStatusBooking(int id, int statusId) {
        return mFSalonApi.changeStatusBooking(id, statusId).flatMap(
            new Function<Respone<List<String>>, ObservableSource<List<String>>>() {
                @Override
                public ObservableSource<List<String>> apply(
                    @NonNull Respone<List<String>> listRespone)
                    throws Exception {
                    return Utils.getResponse(listRespone);
                }
            });
    }

    @Override
    public Observable<BookingOder> postImageByStylist(@NonNull int orderBookingId,
                                                      @NonNull String imagePaths) {
        Map<String, String> params = new HashMap<>();
        if (orderBookingId > OUT_OF_INDEX) {
            params.put(ORDER_BOOKING_ID, String.valueOf(orderBookingId));
        }
        if (imagePaths != null) {
            params.put(IMAGE, imagePaths);
        }
        return mFSalonApi.postImageByStylist(params).flatMap(
            new Function<Respone<BookingOder>, ObservableSource<BookingOder>>() {
                @Override
                public ObservableSource<BookingOder> apply(
                    @NonNull Respone<BookingOder> bookingOderRespone)
                    throws Exception {
                    return Utils.getResponse(bookingOderRespone);
                }
            });
    }

    /**
     * post multi files to server
     *
     * @param files
     * @param mediaType
     * @param folder
     * @return
     */
    @Override
    public Observable<List<String>> postMultiImages(@NonNull List<File> files, String mediaType,
                                                    String folder) {
        List<MultipartBody.Part> parts = new ArrayList<>();
        MultipartBody.Part part;
        for (File file : files) {
            part = createMultiPartBody(file, mediaType, folder);
            if (part == null) {
                break;
            }
            parts.add(part);
        }
        return mFSalonApi.postMultiImages(folder, parts)
            .flatMap(new Function<Respone<List<String>>, ObservableSource<List<String>>>() {
                @Override
                public ObservableSource<List<String>> apply(@NonNull Respone<List<String>>
                                                                listUrls)
                    throws Exception {
                    return Utils.getResponse(listUrls);
                }
            });
    }

    /***
     * @param file: file to upload
     * @param mediaType: media type of file
     * @param folder: folder contents file uploaded
     * @return
     */
    private MultipartBody.Part createMultiPartBody(File file, String mediaType, String folder) {
        if (file == null) {
            return null;
        }
        RequestBody requestFile =
            RequestBody.create(
                MediaType.parse(mediaType), file);
        return MultipartBody.Part.createFormData(POST_IMAGE, file.getName(), requestFile);
    }
}
