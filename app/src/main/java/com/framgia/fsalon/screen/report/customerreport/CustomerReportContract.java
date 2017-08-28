package com.framgia.fsalon.screen.report.customerreport;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface CustomerReportContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetReportSuccess(List<BarEntry> barEntries, int oldCustomer, int newCustomer,
                                List<String> labels);
        void onGetReportFail(String msg);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getReport(String type, long start, long end);
    }
}
