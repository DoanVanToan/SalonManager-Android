package com.framgia.fsalon.data.source.remote;

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
    public Observable<CustomerReportResponse> getBookingReport(String type, long start, long end) {
        Map<String, String> param = new HashMap<>();
        param.put(Constant.ApiParram.START_DATE, String.valueOf(start));
        param.put(Constant.ApiParram.END_DATE, String.valueOf(end));
        if (type != null) {
            param.put(Constant.ApiParram.TYPE_FILTER, type);
        }
        return mFSalonApi.getBookingReport(param).flatMap(
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
}
