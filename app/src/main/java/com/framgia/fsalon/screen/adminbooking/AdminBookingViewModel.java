package com.framgia.fsalon.screen.adminbooking;

/**
 * Exposes the data to be used in the Adminbooking screen.
 */
public class AdminBookingViewModel implements AdminBookingContract.ViewModel {
    private AdminBookingContract.Presenter mPresenter;

    public AdminBookingViewModel() {
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
    public void setPresenter(AdminBookingContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
