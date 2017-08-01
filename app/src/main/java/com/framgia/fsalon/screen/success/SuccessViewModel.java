package com.framgia.fsalon.screen.success;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.framgia.fsalon.data.model.BookingOder;

/**
 * Exposes the data to be used in the Success screen.
 */
public class SuccessViewModel implements SuccessContract.ViewModel {
    private SuccessContract.Presenter mPresenter;
    private BookingOder mBookingOder;
    private AppCompatActivity mActivity;

    public SuccessViewModel(AppCompatActivity activity, BookingOder order) {
        mActivity = activity;
        mBookingOder = order;
    }

    public BookingOder getBookingOder() {
        return mBookingOder;
    }

    public void setBookingOder(BookingOder bookingOder) {
        mBookingOder = bookingOder;
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
    public void setPresenter(SuccessContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void onOkClick(View view) {
        mActivity.finish();
    }
}
