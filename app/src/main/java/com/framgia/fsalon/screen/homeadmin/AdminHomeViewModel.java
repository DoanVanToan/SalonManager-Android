package com.framgia.fsalon.screen.homeadmin;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.screen.customer.CustomerAdapter;
import com.framgia.fsalon.screen.customer.CustomerFragment;
import com.framgia.fsalon.screen.scheduler.SchedulerFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fsalon.screen.homeadmin.AdminHomeViewModel.Tab.TAB_CUSTOMER;
import static com.framgia.fsalon.screen.homeadmin.AdminHomeViewModel.Tab.TAB_PROFILE;
import static com.framgia.fsalon.screen.homeadmin.AdminHomeViewModel.Tab.TAB_RECEIPT;
import static com.framgia.fsalon.screen.homeadmin.AdminHomeViewModel.Tab.TAB_REPORT;
import static com.framgia.fsalon.screen.homeadmin.AdminHomeViewModel.Tab.TAB_SCHEDULER;

/**
 * Exposes the data to be used in the Adminhome screen.
 */
public class AdminHomeViewModel extends BaseObservable implements AdminHomeContract.ViewModel {
    private AdminHomeContract.Presenter mPresenter;
    private AppCompatActivity mActivity;
    private HomePagerAdapter mAdapter;
    private int mTabPosition;
    private BottomNavigationView.OnNavigationItemSelectedListener mListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_scheduler:
                        setTabPosition(Tab.TAB_SCHEDULER);
                        break;
                    case R.id.item_receipt:
                        /**
                         * todo later
                         */
                        setTabPosition(Tab.TAB_RECEIPT);
                        break;
                    case R.id.item_report:
                        /**
                         * todo later
                         */
                        setTabPosition(Tab.TAB_REPORT);
                        break;
                    case R.id.item_customer:
                        /**
                         * todo later
                         */
                        setTabPosition(Tab.TAB_CUSTOMER);
                        break;
                    case R.id.item_profile:
                        /**
                         * todo later
                         */
                        setTabPosition(Tab.TAB_PROFILE);
                        break;
                }
                return true;
            }
        };

    public AdminHomeViewModel(AppCompatActivity activity) {
        mActivity = activity;
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(SchedulerFragment.newInstance());
        fragments.add(CustomerFragment.newInstance());
        mAdapter = new HomePagerAdapter(activity.getSupportFragmentManager(), fragments);
        setAdapter(mAdapter);
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
    public void setPresenter(AdminHomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @IntDef({TAB_SCHEDULER, TAB_CUSTOMER, TAB_RECEIPT, TAB_REPORT, TAB_PROFILE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Tab {
        int TAB_SCHEDULER = 0;
        int TAB_CUSTOMER = 1;
        int TAB_RECEIPT = 2;
        int TAB_REPORT = 3;
        int TAB_PROFILE = 4;
    }

    @Bindable
    public AppCompatActivity getActivity() {
        return mActivity;
    }

    public void setActivity(AppCompatActivity activity) {
        mActivity = activity;
        notifyPropertyChanged(BR.activity);
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

    @Bindable
    public BottomNavigationView.OnNavigationItemSelectedListener getListener() {
        return mListener;
    }

    public void setListener(
        BottomNavigationView.OnNavigationItemSelectedListener listener) {
        mListener = listener;
        notifyPropertyChanged(BR.listener);
    }
}
