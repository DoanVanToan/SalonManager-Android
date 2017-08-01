package com.framgia.fsalon.screen.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;

/**
 * Exposes the data to be used in the Home screen.
 */
public class HomeViewModel extends BaseObservable implements HomeContract.ViewModel {
    public static final int FUNCTION_COUNT = 4;
    public static final int BOOKING_FUNC = 0;
    public static final int DETAIL_FUNC = 1;
    public static final int HISTORY_FUNC = 2;
    public static final int USER_FUNC = 3;
    private HomeContract.Presenter mPresenter;
    private PagerAdapter mPagerAdapter;
    private int mTabPosition;
    private BottomNavigationView.OnNavigationItemSelectedListener mListener;

    public HomeViewModel(AppCompatActivity activity) {
        mPagerAdapter = new HomePagerAdapter(activity.getSupportFragmentManager());
        mListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_book:
                        onBookingClick();
                        break;
                    case R.id.menu_detail:
                        onDetailListClick();
                        break;
                    case R.id.menu_history:
                        onHistoryClick();
                        break;
                    case R.id.menu_user:
                        onUserClick();
                        break;
                    default:
                        break;
                }
                return true;
            }
        };
    }

    @Bindable
    public int getTabPosition() {
        return mTabPosition;
    }

    public BottomNavigationView.OnNavigationItemSelectedListener getListener() {
        return mListener;
    }

    public void setListener(
        BottomNavigationView.OnNavigationItemSelectedListener listener) {
        mListener = listener;
    }

    public void setTabPosition(int tabPosition) {
        mTabPosition = tabPosition;
        notifyPropertyChanged(BR.tabPosition);
    }

    public PagerAdapter getPagerAdapter() {
        return mPagerAdapter;
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
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onUserClick() {
        setTabPosition(Tab.USER_FUNC);
    }

    @Override
    public void onBookingClick() {
        setTabPosition(Tab.BOOKING_FUNC);
    }

    @Override
    public void onDetailListClick() {
        setTabPosition(Tab.DETAIL_FUNC);
    }

    @Override
    public void onHistoryClick() {
        setTabPosition(Tab.HISTORY_FUNC);
    }

    @IntDef({BOOKING_FUNC, DETAIL_FUNC, HISTORY_FUNC, USER_FUNC})
    public @interface Tab {
        int BOOKING_FUNC = 0;
        int DETAIL_FUNC = 1;
        int HISTORY_FUNC = 2;
        int USER_FUNC = 3;
    }
}
