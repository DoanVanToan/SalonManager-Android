package com.framgia.fsalon.screen.report.reportsales;

/**
 * Listens to user actions from the UI ({@link ReportSalesFragment}), retrieves the data and updates
 * the UI as required.
 */
public class ReportSalesPresenter implements ReportSalesContract.Presenter {
    private static final String TAG = ReportSalesPresenter.class.getName();
    private final ReportSalesContract.ViewModel mViewModel;

    public ReportSalesPresenter(ReportSalesContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
