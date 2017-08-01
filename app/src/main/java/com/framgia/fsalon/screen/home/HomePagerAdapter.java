package com.framgia.fsalon.screen.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.framgia.fsalon.screen.booking.BookingFragment;

/**
 * Created by THM on 7/25/2017.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            // TODO: 7/25/2017 return the right fragment
            case HomeViewModel.BOOKING_FUNC:
                return BookingFragment.newInstance();
            case HomeViewModel.DETAIL_FUNC:
                return new Fragment();
            case HomeViewModel.HISTORY_FUNC:
                return new Fragment();
            case HomeViewModel.USER_FUNC:
                return new Fragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return HomeViewModel.FUNCTION_COUNT;
    }
}
