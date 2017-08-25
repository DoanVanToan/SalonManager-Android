package com.framgia.fsalon.screen.report.customerreport;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Listens to user actions from the UI ({@link CustomerReportFragment}),
 * retrieves the data and updates the UI as required.
 */
final class CustomerReportPresenter implements CustomerReportContract.Presenter {
    private static final String TAG = CustomerReportPresenter.class.getName();
    private final CustomerReportContract.ViewModel mViewModel;

    CustomerReportPresenter(CustomerReportContract.ViewModel viewModel) {
        mViewModel = viewModel;
        getReport();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void getReport() {
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            float mult = 120;
            float val1 = (float) (Math.random() * mult) + mult / 3;
            float val2 = (float) (Math.random() * mult) + mult / 3;
            barEntries.add(new BarEntry(i, new float[]{val1, val2}));
        }
        mViewModel.onGetReportSuccess(barEntries);
    }
}
