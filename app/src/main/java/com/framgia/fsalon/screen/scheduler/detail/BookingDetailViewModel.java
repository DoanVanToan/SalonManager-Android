package com.framgia.fsalon.screen.scheduler.detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.utils.Utils;
import com.framgia.fsalon.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Detail screen.
 */
public class BookingDetailViewModel extends BaseObservable
    implements BookingDetailContract.ViewModel {
    private BookingDetailContract.Presenter mPresenter;
    private BookingOder mBookingOder;
    private int mId;
    private int mProgressBarVisibility;
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
    public int getProgressBarVisibility() {
        return mProgressBarVisibility;
    }

    public void setProgressBarVisibility(int progressBarVisibility) {
        mProgressBarVisibility = progressBarVisibility;
        notifyPropertyChanged(BR.progressBarVisibility);
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
        showProgressBar();
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

    @Override
    public void showProgressBar() {
        setProgressBarVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        setProgressBarVisibility(View.GONE);
    }

    @Override
    public void callCustomer() {
        if (ActivityCompat.checkSelfPermission(mNavigator.getContext(),
            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Utils.requestCallPermission(mNavigator.getContext());
        } else {
            onPermissionGranted();
        }
    }

    @Override
    public void editBooking() {
        // TODO: 8/7/2017
    }

    @Override
    public void messageCustomer() {
        // TODO: 8/8/2017
    }

    @Override
    public void onPermissionGranted() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mBookingOder.getPhone()));
        mNavigator.startActivity(intent);
    }

    @Override
    public void onPermissionDenied() {
        mNavigator.showToast(R.string.msg_no_permission);
    }
}
