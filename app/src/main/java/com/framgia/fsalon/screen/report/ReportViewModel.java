package com.framgia.fsalon.screen.report;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.screen.homeadmin.HomePagerAdapter;
import com.framgia.fsalon.screen.report.customerreport.CustomerReportFragment;
import com.framgia.fsalon.screen.report.reportsales.ReportSalesFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fsalon.screen.report.ReportViewModel.TabButton.TAB_BILLS;
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
        fragments.add(ReportSalesFragment.newInstance());
        fragments.add(CustomerReportFragment.newInstance());
        fragments.add(new Fragment());
        mAdapter = new HomePagerAdapter(fragment.getChildFragmentManager(), fragments);
        setAdapter(mAdapter);
    }

    /**
     * TabButton
     */
    @IntDef({TAB_SALES, TAB_CUSTOMERS, TAB_BILLS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TabButton {
        int TAB_SALES = 0;
        int TAB_CUSTOMERS = 1;
        int TAB_BILLS = 2;
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
}
