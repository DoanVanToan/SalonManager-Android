package com.framgia.fsalon.screen.report;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.screen.homeadmin.HomePagerAdapter;
import com.framgia.fsalon.screen.report.billbookingreport.BillBookingReportFragment;
import com.framgia.fsalon.screen.report.customerreport.CustomerReportFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fsalon.screen.report.ReportViewModel.TabButton.TAB_BOOKING;
import static com.framgia.fsalon.screen.report.ReportViewModel.TabButton.TAB_CUSTOMERS;
import static com.framgia.fsalon.screen.report.ReportViewModel.TabButton.TAB_SALES;

/**
 * Exposes the data to be used in the Report screen.
 */
public class ReportViewModel extends BaseObservable implements ReportContract.ViewModel {
    private ReportContract.Presenter mPresenter;
    private HomePagerAdapter mAdapter;
    private int mTabPosition;

    public ReportViewModel(Fragment fragment) {
        List<Fragment> fragments = new ArrayList<>();
        int status = 0;
        String testType = "day";
        long start = 1498900037;
        long end = 1501578437;
        fragments.add(BillBookingReportFragment.newInstance(testType, status, start, end));
        fragments.add(CustomerReportFragment.newInstance(testType, start, end));
        fragments.add(new Fragment());
        mAdapter = new HomePagerAdapter(fragment.getChildFragmentManager(), fragments);
        setAdapter(mAdapter);
    }

    public void onTabFilterClick(@TabButton int tabPosition) {
        setTabPosition(tabPosition);
    }

    @Bindable
    public HomePagerAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(HomePagerAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Bindable
    public int getTabPosition() {
        return mTabPosition;
    }

    public void setTabPosition(int tabPosition) {
        mTabPosition = tabPosition;
        notifyPropertyChanged(BR.tabPosition);
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
    public void setPresenter(ReportContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * TabButton
     */
    @IntDef({TAB_SALES, TAB_CUSTOMERS, TAB_BOOKING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabButton {
        int TAB_SALES = 0;
        int TAB_CUSTOMERS = 1;
        int TAB_BOOKING = 2;
    }
}
