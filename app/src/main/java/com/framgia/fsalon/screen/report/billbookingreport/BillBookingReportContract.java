package com.framgia.fsalon.screen.report.billbookingreport;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.github.mikephil.charting.data.Entry;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface BillBookingReportContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetReportSuccess(List<Entry> barEntries, int totalSale, List<String> colName);
        void onGetReportFail(String msg);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getReport(String type, int status, long start, long end);
    }
}
