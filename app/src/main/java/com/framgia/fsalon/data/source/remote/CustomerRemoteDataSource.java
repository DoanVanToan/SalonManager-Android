package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.model.CustomerResponse;
import com.framgia.fsalon.data.source.CustomerDatasource;
import com.framgia.fsalon.data.source.api.FSalonApi;
import com.framgia.fsalon.utils.Utils;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by huynh on 09-Aug-17.
 */
public class CustomerRemoteDataSource extends BaseRemoteDataSource implements
    CustomerDatasource {
    public CustomerRemoteDataSource(FSalonApi mFSalonApi) {
        super(mFSalonApi);
    }

    @Override
    public Observable<CustomerResponse> getAllCustomers(int page, int perpage) {
        return mFSalonApi.getAllCustomers(page, perpage).flatMap(
            new Function<Respone<CustomerResponse>,
                ObservableSource<CustomerResponse>>() {
                @Override
                public ObservableSource<CustomerResponse> apply(
                    @NonNull Respone<CustomerResponse> bookingCustomerResponseRespone)
                    throws Exception {
                    return Utils.getResponse(bookingCustomerResponseRespone);
                }
            });
    }
}
