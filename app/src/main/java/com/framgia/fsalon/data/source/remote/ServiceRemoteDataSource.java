package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.ServiceBooking;
import com.framgia.fsalon.data.model.ServiceBookingRespond;
import com.framgia.fsalon.data.source.ServiceDataSource;
import com.framgia.fsalon.data.source.api.FSalonApi;
import com.framgia.fsalon.utils.Utils;

import java.util.List;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by THM on 8/1/2017.
 */
public class ServiceRemoteDataSource extends BaseRemoteDataSource implements ServiceDataSource {
    public ServiceRemoteDataSource(FSalonApi fSalonApi) {
        super(fSalonApi);
    }

    @Override
    public Observable<List<Service>> getAllServices() {
        return mFSalonApi.getServices().flatMap(
            new Function<Respone<List<Service>>, ObservableSource<List<Service>>>() {
                @Override
                public ObservableSource<List<Service>> apply(@NonNull Respone<List<Service>>
                                                                 listRespone)
                    throws Exception {
                    return Utils.getResponse(listRespone);
                }
            });
    }

    @Override
    public Observable<ServiceBookingRespond> addServiceBookingByStylistAdmin(
        ServiceBooking serviceBooking) {
        return mFSalonApi.addServiceBooking(serviceBooking)
            .flatMap(
                new Function<Respone<ServiceBookingRespond>,
                    ObservableSource<ServiceBookingRespond>>() {
                    @Override
                    public ObservableSource<ServiceBookingRespond> apply(
                        @NonNull Respone<ServiceBookingRespond> service)
                        throws Exception {
                        return Utils.getResponse(service);
                    }
                });
    }

    @Override
    public Observable<List<Void>> editServiceBookingByStylistAdmin(ServiceBooking serviceBooking) {
        return mFSalonApi.editServiceBooking(serviceBooking)
            .flatMap(
                new Function<Respone<List<Void>>,
                    ObservableSource<List<Void>>>() {
                    @Override
                    public ObservableSource<List<Void>> apply(
                        @NonNull Respone<List<Void>> data)
                        throws Exception {
                        return Utils.getResponse(data);
                    }
                });
    }

    @Override
    public Observable<List<Void>> deleteServiceBookingByStylistAdmin(int id) {
        return mFSalonApi.deleteServiceBooking(id)
            .flatMap(new Function<Respone<List<Void>>, ObservableSource<List<Void>>>() {
                @Override
                public ObservableSource<List<Void>> apply(@NonNull Respone<List<Void>> listRespone)
                    throws Exception {
                    return Utils.getResponse(listRespone);
                }
            });
    }
}
