package com.framgia.fsalon.data.source.api;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingResponse;
import com.framgia.fsalon.data.model.Salon;
import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.data.model.UserRespone;

import java.util.List;
import java.util.Map;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface FSalonApi {

    @POST("api/v0/login")
    Observable<Respone<UserRespone>> login(@Query("email_or_phone") String account,
                                           @Query("password") String passWord);

    @GET("api/v0/get-salons")
    Observable<Respone<List<Salon>>> getSalon();

    @GET("api/v0/get-stylist-by-salonId/{id}")
    Observable<Respone<List<Stylist>>> getStylistBySalonId(@Path("id") int id);

    @GET("api/v0/get-render-by-depart-stylist")
    Observable<Respone<BookingResponse>> getBookings(@QueryMap Map<String, String> parrams);

    @POST("api/v0/user_booking")
    Observable<Respone<BookingOder>> book(@QueryMap Map<String, String> parrams);

    @POST("api/v0/register")
    Observable<Respone<UserRespone>> registry(@Query("email") String email,
                                           @Query("password") String passWord,
                                              @Query("password_confirmation") String rePassword,
                                              @Query("name") String name,
                                              @Query("phone") String phone);

}
