package com.framgia.fsalon.screen.scheduler.detail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Detail screen.
 */
public class BookingDetailViewModel extends BaseObservable
    implements BookingDetailContract.ViewModel {
    private BookingDetailContract.Presenter mPresenter;
    private BookingOder mBookingOder;
    private int mId;
    private boolean mIsFinish = true;
    private Navigator mNavigator;
    private SwipeRefreshLayout.OnRefreshListener mListener =
        new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getBookingById(mId);
            }
        };

    public BookingDetailViewModel(AppCompatActivity activity, int id) {
        mNavigator = new Navigator(activity);
        mId = id;
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
    public boolean isFinish() {
        return mIsFinish;
    }

    public void setFinish(boolean finish) {
        mIsFinish = finish;
        notifyPropertyChanged(BR.finish);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getBookingById(mId);
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(BookingDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetBookingError(String msg) {
        mNavigator.showToast(msg);
    }

    @Override
    public void onGetBookingSuccess(BookingOder bookingOder) {
        setBookingOder(bookingOder);
    }

    @Override
    public void finishRefresh() {
        setFinish(true);
    }
}
