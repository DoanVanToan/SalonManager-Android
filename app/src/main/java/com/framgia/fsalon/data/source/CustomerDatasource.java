package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.CustomerResponse;

import io.reactivex.Observable;

/**
 * Created by huynh on 09-Aug-17.
 */
public interface CustomerDatasource {
    Observable<CustomerResponse> getAllCustomers(int page, int perPage);
}
