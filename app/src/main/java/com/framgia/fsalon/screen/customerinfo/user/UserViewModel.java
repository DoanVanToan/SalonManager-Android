package com.framgia.fsalon.screen.customerinfo.user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.utils.Utils;
import com.framgia.fsalon.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the User screen.
 */
public class UserViewModel extends BaseObservable implements UserContract.ViewModel {
    private UserContract.Presenter mPresenter;
    private User mUser;
    private BookingOder mBookingOder;
    private Navigator mNavigator;

    public UserViewModel(AppCompatActivity activity, User user) {
        mNavigator = new Navigator(activity);
        mUser = user;
    }

    @Bindable
    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
        notifyPropertyChanged(BR.user);
    }

    @Bindable
    public BookingOder getBookingOder() {
        return mBookingOder;
    }

    public void setBookingOder(BookingOder bookingOder) {
        mBookingOder = bookingOder;
        notifyPropertyChanged(BR.bookingOder);
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
    public void setPresenter(UserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCallClick() {
        if (ActivityCompat.checkSelfPermission(mNavigator.getContext(),
            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Utils.requestCallPermission(mNavigator.getContext());
        } else {
            onPermissionGranted();
        }
    }

    @Override
    public void onMessageClick() {
    }

    @Override
    public void onPermissionGranted() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mBookingOder.getPhone()));
        mNavigator.startActivity(intent);
    }

    @Override
    public void onGetBookingSuccess(BookingOder oder) {
        setBookingOder(oder);
    }

    @Override
    public void onGetBookingError(String msg) {
        mNavigator.showToast(msg);
    }

    @Override
    public void onPermissionDenied() {
        mNavigator.showToast(R.string.msg_no_permission);
    }
}
