package com.framgia.fsalon.screen.report.billbookingreport;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.utils.formatter.ColNameIAxisValueFormatter;
import com.framgia.fsalon.utils.formatter.IntIAxisValueFormatter;
import com.framgia.fsalon.utils.formatter.IntValueFormatter;
import com.framgia.fsalon.utils.navigator.Navigator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Billreport screen.
 */
public class BillBookingReportViewModel extends BaseObservable
    implements BillBookingReportContract.ViewModel {
    private BillBookingReportContract.Presenter mPresenter;
    private IAxisValueFormatter mYIAxisValueFormatter;
    private IAxisValueFormatter mXIAxisValueFormatter;
    private IValueFormatter mIValueFormatter;
    private List<Entry> mEntries;
    private String mLabel;
    private Navigator mNavigator;
    private String mTotalSale;

    public BillBookingReportViewModel(Fragment fragment) {
        mNavigator = new Navigator(fragment);
        mEntries = new ArrayList<>();
        setLabel(fragment.getString(R.string.title_bill_label));
        mYIAxisValueFormatter = new IntIAxisValueFormatter();
        mIValueFormatter = new IntValueFormatter();
        mXIAxisValueFormatter = null;
    }
    // TODO: 8/28/2017  tao formatter moi

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(BillBookingReportContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public IAxisValueFormatter getYIAxisValueFormatter() {
        return mYIAxisValueFormatter;
    }

    public void setYIAxisValueFormatter(
        IAxisValueFormatter yIAxisValueFormatter) {
        mYIAxisValueFormatter = yIAxisValueFormatter;
        notifyPropertyChanged(BR.yIAxisValueFormatter);
    }

    @Bindable
    public IAxisValueFormatter getXIAxisValueFormatter() {
        return mXIAxisValueFormatter;
    }

    public void setXIAxisValueFormatter(
        IAxisValueFormatter xIAxisValueFormatter) {
        mXIAxisValueFormatter = xIAxisValueFormatter;
        notifyPropertyChanged(BR.xIAxisValueFormatter);
    }

    @Bindable
    public IValueFormatter getIValueFormatter() {
        return mIValueFormatter;
    }

    public void setIValueFormatter(IValueFormatter iValueFormatter) {
        mIValueFormatter = iValueFormatter;
        notifyPropertyChanged(BR.iValueFormatter);
    }

    @Bindable
    public List<Entry> getEntries() {
        return mEntries;
    }

    public void setEntries(List<Entry> entries) {
        mEntries = entries;
        notifyPropertyChanged(BR.entries);
    }

    @Bindable
    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
        notifyPropertyChanged(BR.label);
    }

    @Bindable
    public String getTotalSale() {
        return mTotalSale;
    }

    public void setTotalSale(String totalSale) {
        mTotalSale = totalSale;
        notifyPropertyChanged(BR.totalSale);
    }

    @Override
    public void onGetReportSuccess(List<Entry> barEntries, int totalSale, List<String> colName) {
        setEntries(barEntries);
        setTotalSale(String.valueOf(totalSale));
        setXIAxisValueFormatter(new ColNameIAxisValueFormatter(colName));
    }

    @Override
    public void onGetReportFail(String msg) {
        mNavigator.showToast(msg);
    }
}
