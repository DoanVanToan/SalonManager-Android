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

import static com.framgia.fsalon.utils.Constant.ApiParram.NON_FILTER;
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
    private static final String MONTH_INPUT = "month_input";
    private static final String WEEK_INPUT = "week_input";
    public static final String FILTER_DAY = "day";
    public static final String FILTER_MONTH = "month";
    public static final String FILTER_WEEK = "week";
    public static final String STATUS = "status";

    public ManageBookingRemoteDataSource(FSalonApi api) {
        super(api);
    }

    @Override
    public Observable<List<ManageBookingResponse>> getListBooking(String filterChoice, int page,
                                                                  int perpage,
                                                                  String status, String startDate,
                                                                  String endDate,
                                                                  String monthInput,
                                                                  String weekInput) {
        return mFSalonApi.getManageBookings(createParams(filterChoice, page, perpage, status,
            startDate, endDate, monthInput, weekInput))
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
                                             String status, String startDate, String endDate,
                                             String monthInput, String weekInput) {
        Map<String, String> params = new HashMap<>();
        if (page != OUT_OF_INDEX) {
            params.put(PAGE, String.valueOf(page));
        }
        if (perpage != OUT_OF_INDEX) {
            params.put(PER_PAGE, String.valueOf(page));
        }
        if (!status.equals(NON_FILTER)) {
            params.put(STATUS, status);
        }
        switch (filterChoice) {
            case FILTER_DAY:
                if (!startDate.equals(NON_FILTER)) {
                    params.put(START_DATE, startDate);
                }
                if (!endDate.equals(NON_FILTER)) {
                    params.put(END_DATE, endDate);
                }
                break;
            case FILTER_MONTH:
                if (!monthInput.equals(NON_FILTER)) {
                    params.put(MONTH_INPUT, monthInput);
                }
                break;
            case FILTER_WEEK:
                if (!weekInput.equals(NON_FILTER)) {
                    params.put(WEEK_INPUT, weekInput);
                }
                break;
            default:
                break;
        }
        return params;
    }
}