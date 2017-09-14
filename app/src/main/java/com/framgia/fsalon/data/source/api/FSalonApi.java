package com.framgia.fsalon.data.source.api;

import com.framgia.fsalon.data.model.BillReportResponse;
import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingResponse;
import com.framgia.fsalon.data.model.CustomerReportResponse;
import com.framgia.fsalon.data.model.CustomerResponse;
import com.framgia.fsalon.data.model.ListBillRespond;
import com.framgia.fsalon.data.model.ManageBookingResponse;
import com.framgia.fsalon.data.model.Salon;
import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.ServiceBooking;
import com.framgia.fsalon.data.model.ServiceBookingRespond;
import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.data.model.UserRespone;

import java.util.List;
import java.util.Map;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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
    @GET("api/v0/bill-by-customer-id-with-images")
    Observable<Respone<List<BillResponse>>> getBillsByCustomerId(
        @Query("customer_id") int customerId);
    @GET("api/v0/report-customer")
    Observable<Respone<CustomerReportResponse>> getCustomerReport(@QueryMap Map<String, String>
                                                                      parrams);
    @PUT("api/v0/change-status-booking/{id}")
    Observable<Respone<List<String>>> changeStatusBooking(@Path("id") int id,
                                                          @Query("status") int statusId);
    @POST("api/v0/order-booking/stylist-upload-image")
    Observable<Respone<BookingOder>> postImageByStylist(@QueryMap Map<String, String> params);
    @Multipart
    @POST("api/v0/media-upload/{folder}")
    Observable<Respone<List<String>>> postMultiImages(@Path("folder") String folder,
                                                      @Part List<MultipartBody.Part> files);
    @GET("api/v0/report-bill")
    Observable<Respone<BillReportResponse>> getBillReport(@QueryMap Map<String, String> parrams);
    @POST("api/v0/add-booking-service")
    Observable<Respone<ServiceBookingRespond>> addServiceBooking(
        @Body ServiceBooking serviceBooking);
    @PUT("api/v0/add-booking-service")
    Observable<Respone<List<Void>>> editServiceBooking(@Body ServiceBooking serviceBooking);
    @DELETE("api/v0/add-booking-service/{order_item_id}")
    Observable<Respone<List<Void>>> deleteServiceBooking(@Path("order_item_id") int id);
}

