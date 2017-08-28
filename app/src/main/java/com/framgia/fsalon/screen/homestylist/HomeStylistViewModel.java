package com.framgia.fsalon.screen.homestylist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.databinding.library.baseAdapters.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.screen.stylistbooking.StylistBookingFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.fsalon.screen.homestylist.HomeStylistViewModel.Tab.TAB_BOOKING;

/**
 * Exposes the data to be used in the Homestylist screen.
 */
public class HomeStylistViewModel extends BaseObservable implements HomeStylistContract.ViewModel {
    private HomeStylistContract.Presenter mPresenter;
    private AppCompatActivity mActivity;
    private StylistHomePagerAdapter mAdapter;
    private int mTabPosition;
    private BottomNavigationView.OnNavigationItemSelectedListener mListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_booking:
                        setTabPosition(TAB_BOOKING);
                        break;
                    default:
                        break;
                }
                return true;
            }
        };

    public HomeStylistViewModel(AppCompatActivity activity) {
        mActivity = activity;
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(StylistBookingFragment.newInstance());
        mAdapter = new StylistHomePagerAdapter(activity.getSupportFragmentManager(), fragments);
        setAdapter(mAdapter);
    }

    @Bindable
    public StylistHomePagerAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(StylistHomePagerAdapter adapter) {
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

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(HomeStylistContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * Tab for each fragment in stylist home screen
     */
    @IntDef({TAB_BOOKING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Tab {
        int TAB_BOOKING = 0;
    }
}
