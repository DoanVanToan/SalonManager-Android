package com.framgia.fsalon.screen.report.reportsales;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.framgia.fsalon.BR;
import com.github.mikephil.charting.data.LineData;

/**
 * Exposes the data to be used in the ReportSales screen.
 */
public class ReportSalesViewModel extends BaseObservable implements ReportSalesContract.ViewModel {
    private ReportSalesContract.Presenter mPresenter;
    private LineData mLineData;

    public ReportSalesViewModel() {
    }
    @Bindable
    public LineData getLineData() {
        return mLineData;
    }

    public void setLineData(LineData lineData) {
        mLineData = lineData;
        notifyPropertyChanged(BR.lineData);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ReportSalesContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
