package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.BillReportResponse;
import com.framgia.fsalon.data.model.CustomerReportResponse;

import io.reactivex.Observable;

/**
 * Created by THM on 8/25/2017.
 */
public interface ReportDataSource {
    Observable<CustomerReportResponse> getCustomerReport(String type, long start, long end);
    Observable<BillReportResponse> getBillReport(String type, int status, long start, long end);
}
