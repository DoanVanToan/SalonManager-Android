package com.framgia.fsalon.screen.user;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.User;

/**
 * Exposes the data to be used in the User screen.
 */
public class UserViewModel implements UserContract.ViewModel {
    private UserContract.Presenter mPresenter;
    private User mUser;
    private BookingOder mBookingOder;

    public UserViewModel() {
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
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
    public void setPresenter(UserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCallClick() {
    }

    @Override
    public void onMessageClick() {
    }
}
