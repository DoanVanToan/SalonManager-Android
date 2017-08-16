package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.CustomerResponse;
import com.framgia.fsalon.data.source.remote.CustomerRemoteDataSource;

import io.reactivex.Observable;

/**
 * Created by huynh on 09-Aug-17.
 */
public class CustomerRepository implements CustomerDatasource {
    private CustomerDatasource mCustomerDatasource;

    public CustomerRepository(
        CustomerRemoteDataSource customerBookingRemoteDataSource) {
        mCustomerDatasource = customerBookingRemoteDataSource;
    }

    @Override
    public Observable<CustomerResponse> getAllCustomers(int page, int perpage) {
        return mCustomerDatasource.getAllCustomers(page, perpage);
    }
}
