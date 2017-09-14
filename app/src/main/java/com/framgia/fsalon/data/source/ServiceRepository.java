package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.ServiceBooking;
import com.framgia.fsalon.data.model.ServiceBookingRespond;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by MyPC on 02/08/2017.
 */
public class ServiceRepository implements ServiceDataSource {
    private ServiceDataSource mRemoteDatSource;

    public ServiceRepository(ServiceDataSource remoteDatSource) {
        mRemoteDatSource = remoteDatSource;
    }

    @Override
    public Observable<List<Service>> getAllServices() {
        return mRemoteDatSource.getAllServices();
    }

    @Override
    public Observable<ServiceBookingRespond> addServiceBookingByStylistAdmin(
        ServiceBooking serviceBooking) {
        return mRemoteDatSource.addServiceBookingByStylistAdmin(serviceBooking);
    }

    @Override
    public Observable<List<Void>> editServiceBookingByStylistAdmin(ServiceBooking serviceBooking) {
        return mRemoteDatSource.editServiceBookingByStylistAdmin(serviceBooking);
    }

    @Override
    public Observable<List<Void>> deleteServiceBookingByStylistAdmin(int id) {
        return mRemoteDatSource.deleteServiceBookingByStylistAdmin(id);
    }
}