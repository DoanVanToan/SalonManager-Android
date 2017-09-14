package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.ServiceBooking;
import com.framgia.fsalon.data.model.ServiceBookingRespond;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by THM on 8/1/2017.
 */
public interface ServiceDataSource {
    Observable<List<Service>> getAllServices();
    Observable<ServiceBookingRespond> addServiceBookingByStylistAdmin(ServiceBooking
                                                                          serviceBooking);
    Observable<List<Void>> editServiceBookingByStylistAdmin(ServiceBooking serviceBooking);
    Observable<List<Void>> deleteServiceBookingByStylistAdmin(int id);
}
