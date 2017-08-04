package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.BillRequest;
import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.data.source.remote.BillRemoteDataSource;

import io.reactivex.Observable;

/**
 * Created by MyPC on 03/08/2017.
 */
public class BillRepository implements BillDataSource {
    private BillRemoteDataSource mRemoteDataSource;

    public BillRepository(BillRemoteDataSource remoteDataSource) {
        mRemoteDataSource = remoteDataSource;
    }
    @Override
    public Observable<BillResponse> createBill(BillRequest billRequest) {
        return mRemoteDataSource.createBill(billRequest);
    }
}
