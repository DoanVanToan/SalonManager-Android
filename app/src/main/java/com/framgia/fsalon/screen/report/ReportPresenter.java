package com.framgia.fsalon.screen.report;

/**
 * Listens to user actions from the UI ({@link ReportFragment}), retrieves the data and updates
 * the UI as required.
 */
public class ReportPresenter implements ReportContract.Presenter {
    private static final String TAG = ReportPresenter.class.getName();
    private final ReportContract.ViewModel mViewModel;

    ReportPresenter(ReportContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
