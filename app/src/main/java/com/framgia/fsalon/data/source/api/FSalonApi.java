package com.framgia.fsalon.data.source.api;

import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingResponse;
import com.framgia.fsalon.data.model.CustomerResponse;
import com.framgia.fsalon.data.model.ListBillRespond;
import com.framgia.fsalon.data.model.ManageBookingResponse;
import com.framgia.fsalon.data.model.Salon;
import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.data.model.UserRespone;

import java.util.List;
import java.util.Map;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface FSalonApi {
    @POST("api/v0/login")
    Observable<Respone<UserRespone>> login(@Query("email_or_phone") String account,
                                           @Query("password") String passWord);
    @POST("api/v0/logout")
    Observable<Respone<List<String>>> logout();
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
    @GET("api/v0/filter-order-booking")
    Observable<Respone<List<ManageBookingResponse>>> getManageBookings(@QueryMap Map<String, String>
                                                                           parrams);
    @GET("api/v0/service")
    Observable<Respone<List<Service>>> getServices();
    @GET("api/v0/get_last_booking_by_phone")
    Observable<Respone<BookingOder>> getBookingByPhone(@Query("phone") String phone);
    @GET("api/v0/get_booking_by_id/{id}")
    Observable<Respone<BookingOder>> getBookingById(@Path("id") int id);
    @POST("api/v0/bill")
    Observable<Respone<BillResponse>> createBill(@QueryMap Map<String, String> params);
    @GET("api/v0/user-by-phone")
    Observable<Respone<User>> getCustomerByPhone(@Query("phone") String phone);
    @GET("api/v0/filter-bill")
    Observable<Respone<List<ListBillRespond>>> filterBills(@QueryMap Map<String, String> params);
    @GET("/api/v0/get-custommer")
    Observable<Respone<CustomerResponse>> getAllCustomers(@Query("page") int page,
                                                          @Query("perpage") int perPage);
    @GET("api/v0/bill/{id}")
    Observable<Respone<BillResponse>> getBillById(@Path("id") int id);
    @GET("/api/v0/filter-customer")
    Observable<Respone<CustomerResponse>> searchCustomer(@Query("keyword") String keyword,
                                                         @Query("per_page") int perPage,
                                                         @Query("page") int page);
    @PUT("api/v0/bill/{id}")
    Observable<Respone<BillResponse>> editBill(@Path("id") int id,
                                               @QueryMap Map<String, String> params);
    // TODO: 22/08/2017  call @GET API
    Observable<Respone<List<ListBillRespond>>> getBillsByCustomerId(@Path("id") int id);
}

