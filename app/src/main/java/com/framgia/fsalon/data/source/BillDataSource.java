package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.BillRequest;
import com.framgia.fsalon.data.model.BillResponse;

import io.reactivex.Observable;

/**
 * Created by MyPC on 03/08/2017.
 */
public interface BillDataSource {
    Observable<BillResponse> createBill(BillRequest billRequest);
}
