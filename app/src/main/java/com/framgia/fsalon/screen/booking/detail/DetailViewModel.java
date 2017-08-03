package com.framgia.fsalon.screen.booking.detail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BookingOder;

/**
 * Exposes the data to be used in the Detail screen.
 */
public class DetailViewModel extends BaseObservable implements DetailContract.ViewModel {
    private DetailContract.Presenter mPresenter;
    private BookingOder mBookingOder;
    private boolean mIsHide;
    private String mMessage;
    private Fragment mFragment;
    private boolean mIsFinish = true;
    private SwipeRefreshLayout.OnRefreshListener mListener =
        new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onStart();
            }
        };

    public DetailViewModel(Fragment fragment) {
        mFragment = fragment;
    }

    public SwipeRefreshLayout.OnRefreshListener getListener() {
        return mListener;
    }

    public void setListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mListener = listener;
    }

    @Bindable
    public BookingOder getBookingOder() {
        return mBookingOder;
    }

    public void setBookingOder(BookingOder bookingOder) {
        mBookingOder = bookingOder;
        notifyPropertyChanged(BR.bookingOder);
    }

    @Bindable
    public boolean isHide() {
        return mIsHide;
    }

    public void setHide(boolean hide) {
        mIsHide = hide;
        notifyPropertyChanged(BR.hide);
    }

    @Bindable
    public boolean isFinish() {
        return mIsFinish;
    }

    public void setFinish(boolean finish) {
        mIsFinish = finish;
        notifyPropertyChanged(BR.finish);
    }

    @Bindable
    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
        notifyPropertyChanged(BR.message);
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

    @Override
    public void onGetBookingError(String msg) {
        setHide(false);
        setMessage(msg);
    }

    @Override
    public void onGetBookingSuccess(BookingOder bookingOder) {
        setBookingOder(bookingOder);
        setHide(true);
    }

    @Override
    public void onBookingEmpty() {
        setHide(false);
        setMessage(mFragment.getContext().getString(R.string.title_no_booking));
    }

    @Override
    public void onNotLogin() {
        setHide(false);
        setMessage(mFragment.getContext().getString(R.string.title_no_booking));
    }

    @Override
    public void finishRefresh() {
        setFinish(true);
    }
}
