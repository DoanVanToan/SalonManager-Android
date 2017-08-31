package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.BillReportResponse;
import com.framgia.fsalon.data.model.CustomerReportResponse;
import com.framgia.fsalon.data.source.remote.ReportRemoteDataSource;

import io.reactivex.Observable;

/**
 * Created by THM on 8/25/2017.
 */
public class ReportRepository implements ReportDataSource {
    private ReportRemoteDataSource mReportRemoteDataSource;

    public ReportRepository(
        ReportRemoteDataSource reportRemoteDataSource) {
        mReportRemoteDataSource = reportRemoteDataSource;
    }

    @Override
    public Observable<CustomerReportResponse> getCustomerReport(String type, long start, long end) {
        return mReportRemoteDataSource.getCustomerReport(type, start, end);
    }

    @Override
    public Observable<BillReportResponse> getBillReport(String type, int status, long start,
                                                        long end) {
        return mReportRemoteDataSource.getBillReport(type, status, start, end);
    }
}
