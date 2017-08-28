package com.framgia.fsalon.screen.report.customerreport;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.utils.formatter.ColNameIAxisValueFormatter;
import com.framgia.fsalon.utils.formatter.IntIAxisValueFormatter;
import com.framgia.fsalon.utils.formatter.IntValueFormatter;
import com.framgia.fsalon.utils.navigator.Navigator;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Reportcustomer screen.
 */
public class CustomerReportViewModel extends BaseObservable
    implements CustomerReportContract.ViewModel {
    private static final int BAR_NUMBER = 2;
    private CustomerReportContract.Presenter mPresenter;
    private List<BarEntry> mBarEntries;
    private IAxisValueFormatter mYIAxisValueFormatter;
    private IAxisValueFormatter mXIAxisValueFormatter;
    private IValueFormatter mIValueFormatter;
    private int[] mColors = {R.color.color_red, R.color.color_cyan_400};
    private String[] mLabels = new String[BAR_NUMBER];
    private String mLegend;
    private Context mContext;
    private Navigator mNavigator;
    private String mOldCustomer;
    private String mNewCustomer;

    public CustomerReportViewModel(Fragment fragment) {
        mContext = fragment.getContext();
        mNavigator = new Navigator(fragment);
        String[] labels = {mContext.getResources().getString(R.string.title_customer_old),
            mContext.getResources().getString(R.string.title_customer_new)};
        setLabels(labels);
        mBarEntries = new ArrayList<>();
        mYIAxisValueFormatter = new IntIAxisValueFormatter();
        mIValueFormatter = new IntValueFormatter();
        mXIAxisValueFormatter = null;
        setLegend(mContext.getResources().getString(R.string.title_customer));
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
    public void setPresenter(CustomerReportContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public List<BarEntry> getBarEntries() {
        return mBarEntries;
    }

    public void setBarEntries(List<BarEntry> barEntries) {
        mBarEntries = barEntries;
        notifyPropertyChanged(BR.barEntries);
    }

    @Bindable
    public IAxisValueFormatter getYIAxisValueFormatter() {
        return mYIAxisValueFormatter;
    }

    public void setYIAxisValueFormatter(IAxisValueFormatter yIAxisValueFormatter) {
        mYIAxisValueFormatter = yIAxisValueFormatter;
        notifyPropertyChanged(BR.yIAxisValueFormatter);
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
    public IAxisValueFormatter getXIAxisValueFormatter() {
        return mXIAxisValueFormatter;
    }

    public void setXIAxisValueFormatter(IAxisValueFormatter xIAxisValueFormatter) {
        mXIAxisValueFormatter = xIAxisValueFormatter;
        notifyPropertyChanged(BR.xIAxisValueFormatter);
    }

    @Bindable
    public String getOldCustomer() {
        return mOldCustomer;
    }

    public void setOldCustomer(String oldCustomer) {
        mOldCustomer = oldCustomer;
        notifyPropertyChanged(BR.oldCustomer);
    }

    @Bindable
    public String getNewCustomer() {
        return mNewCustomer;
    }

    public void setNewCustomer(String newCustomer) {
        mNewCustomer = newCustomer;
        notifyPropertyChanged(BR.newCustomer);
    }

    @Bindable
    public int[] getColors() {
        return mColors;
    }

    public void setColors(int[] colors) {
        mColors = colors;
        notifyPropertyChanged(BR.colors);
    }

    public String getLegend() {
        return mLegend;
    }

    public void setLegend(String legend) {
        mLegend = legend;
    }

    public String[] getLabels() {
        return mLabels;
    }

    public void setLabels(String[] labels) {
        mLabels = labels;
    }

    @Override
    public void onGetReportSuccess(List<BarEntry> barEntries, int oldCustomer, int newCustomer,
                                   List<String> labels) {
        setBarEntries(barEntries);
        setOldCustomer(String.valueOf(oldCustomer));
        setNewCustomer(String.valueOf(newCustomer));
        setXIAxisValueFormatter(new ColNameIAxisValueFormatter(labels));
    }

    @Override
    public void onGetReportFail(String msg) {
        mNavigator.showToast(msg);
    }
}
