package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.model.ManageBookingResponse;
import com.framgia.fsalon.data.source.ManageBookingDatasource;
import com.framgia.fsalon.data.source.api.FSalonApi;
import com.framgia.fsalon.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

import static com.framgia.fsalon.utils.Constant.ApiParram.PAGE;
import static com.framgia.fsalon.utils.Constant.ApiParram.PER_PAGE;
import static com.framgia.fsalon.utils.Constant.OUT_OF_INDEX;

/**
 * Created by beepi on 01/08/2017.
 */
public class ManageBookingRemoteDataSource extends BaseRemoteDataSource
    implements ManageBookingDatasource {
    private static final String START_DATE = "start_date";
    private static final String END_DATE = "end_date";
    public static final String FILTER_DAY = "day";
    public static final String FILTER_SPACE = "space";
    public static final String STATUS = "status";
    public static final String FILTER_TYPE = "type";

    public ManageBookingRemoteDataSource(FSalonApi api) {
        super(api);
    }

    @Override
    public Observable<List<ManageBookingResponse>> getListBooking(String filterChoice, int page,
                                                                  int perpage, int status,
                                                                  int startDate, int endDate) {
        return mFSalonApi.getManageBookings(createParams(filterChoice, page, perpage, status,
            startDate, endDate))
            .flatMap(new Function<Respone<List<ManageBookingResponse>>,
                ObservableSource<List<ManageBookingResponse>>>() {
                @Override
                public ObservableSource<List<ManageBookingResponse>> apply(
                    @NonNull Respone<List<ManageBookingResponse>> manageBookingResponseRespone)
                    throws Exception {
                    return Utils.getResponse(manageBookingResponseRespone);
                }
            });
    }

    private Map<String, String> createParams(String filterChoice, int page, int perpage,
                                             int status, int startDate, int endDate) {
        Map<String, String> params = new HashMap<>();
        params.put(FILTER_TYPE, filterChoice);
        if (page != OUT_OF_INDEX) {
            params.put(PAGE, String.valueOf(page));
        }
        if (perpage != OUT_OF_INDEX) {
            params.put(PER_PAGE, String.valueOf(page));
        }
        if (status != OUT_OF_INDEX) {
            params.put(STATUS, String.valueOf(status));
        }
        switch (filterChoice) {
            case FILTER_DAY:
                if (startDate > 0) {
                    params.put(START_DATE, String.valueOf(startDate));
                }
                break;
            case FILTER_SPACE:
                if (startDate > 0) {
                    params.put(START_DATE, String.valueOf(startDate));
                }
                if (endDate > 0) {
                    params.put(END_DATE, String.valueOf(endDate));
                }
                break;
            default:
                break;
        }
        return params;
    }
}
