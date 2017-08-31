package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.model.BillReportResponse;
import com.framgia.fsalon.data.model.CustomerReportResponse;
import com.framgia.fsalon.data.source.ReportDataSource;
import com.framgia.fsalon.data.source.api.FSalonApi;
import com.framgia.fsalon.utils.Constant;
import com.framgia.fsalon.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by THM on 8/25/2017.
 */
public class ReportRemoteDataSource extends BaseRemoteDataSource implements ReportDataSource {
    public ReportRemoteDataSource(FSalonApi fSalonApi) {
        super(fSalonApi);
    }

    @Override
    public Observable<CustomerReportResponse> getCustomerReport(String type, long start, long end) {
        Map<String, String> param = new HashMap<>();
        param.put(Constant.ApiParram.START_DATE, String.valueOf(start));
        param.put(Constant.ApiParram.END_DATE, String.valueOf(end));
        if (type != null) {
            param.put(Constant.ApiParram.TYPE_FILTER, type);
        }
        return mFSalonApi.getCustomerReport(param).flatMap(
            new Function<Respone<CustomerReportResponse>,
                ObservableSource<CustomerReportResponse>>() {
                @Override
                public ObservableSource<CustomerReportResponse> apply(
                    @NonNull Respone<CustomerReportResponse> customerReportResponseRespone)
                    throws Exception {
                    return Utils.getResponse(customerReportResponseRespone);
                }
            });
    }

    @Override
    public Observable<BillReportResponse> getBillReport(String type, int status, long start,
                                                        long end) {
        Map<String, String> param = new HashMap<>();
        param.put(Constant.ApiParram.START_DATE, String.valueOf(start));
        param.put(Constant.ApiParram.END_DATE, String.valueOf(end));
        if (type != null) {
            param.put(Constant.ApiParram.TYPE_FILTER, type);
        }
        if (status >= 0) {
            param.put(Constant.ApiParram.STATUS, String.valueOf(status));
        }
        return mFSalonApi.getBillReport(param).flatMap(
            new Function<Respone<BillReportResponse>,
                ObservableSource<BillReportResponse>>() {
                @Override
                public ObservableSource<BillReportResponse> apply(
                    @NonNull Respone<BillReportResponse> billReportResponseRespone)
                    throws Exception {
                    return Utils.getResponse(billReportResponseRespone);
                }
            });
    }
}
