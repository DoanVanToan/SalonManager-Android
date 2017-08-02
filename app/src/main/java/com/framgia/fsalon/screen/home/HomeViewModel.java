package com.framgia.fsalon.screen.home;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.screen.booking.BookingFragment;
import com.framgia.fsalon.screen.booking.detail.DetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Home screen.
 */
public class HomeViewModel extends BaseObservable implements HomeContract.ViewModel {
    public static final int BOOKING_FUNC = 0;
    public static final int DETAIL_FUNC = 1;
    public static final int HISTORY_FUNC = 2;
    public static final int USER_FUNC = 3;
    private HomeContract.Presenter mPresenter;
    private PagerAdapter mPagerAdapter;
    private int mTabPosition;
    private BottomNavigationView.OnNavigationItemSelectedListener mListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_book:
                        setTabPosition(Tab.BOOKING_FUNC);
                        break;
                    case R.id.menu_detail:
                        setTabPosition(Tab.DETAIL_FUNC);
                        break;
                    case R.id.menu_history:
                        setTabPosition(Tab.HISTORY_FUNC);
                        break;
                    case R.id.menu_user:
                        setTabPosition(Tab.USER_FUNC);
                        break;
                    default:
                        break;
                }
                return true;
            }
        };

    public HomeViewModel(AppCompatActivity activity) {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BookingFragment.newInstance());
        fragments.add(DetailFragment.newInstance());
        mPagerAdapter = new HomePagerAdapter(activity.getSupportFragmentManager(), fragments);
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

    @IntDef({BOOKING_FUNC, DETAIL_FUNC, HISTORY_FUNC, USER_FUNC})
    public @interface Tab {
        int BOOKING_FUNC = 0;
        int DETAIL_FUNC = 1;
        int HISTORY_FUNC = 2;
        int USER_FUNC = 3;
    }
}
