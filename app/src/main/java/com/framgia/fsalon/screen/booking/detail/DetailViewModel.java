package com.framgia.fsalon.screen.booking.detail;

import android.view.View;

import com.framgia.fsalon.data.model.BookingOder;

/**
 * Exposes the data to be used in the Detail screen.
 */
public class DetailViewModel implements DetailContract.ViewModel {
    private DetailContract.Presenter mPresenter;
    private BookingOder mBookingOder;
    private boolean mIsHide;

    public DetailViewModel() {
    }

    public BookingOder getBookingOder() {
        return mBookingOder;
    }

    public void setBookingOder(BookingOder bookingOder) {
        mBookingOder = bookingOder;
    }

    public boolean isHide() {
        return mIsHide;
    }

    public void setHide(boolean hide) {
        mIsHide = hide;
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
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void onEditClicked(View view) {
        // TODO: 7/20/2017 switch to edit activity
    }
}
