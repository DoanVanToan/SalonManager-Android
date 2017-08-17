package com.framgia.fsalon.screen.editbooking;

/**
 * Exposes the data to be used in the Adminbooking screen.
 */
public class EditBookingViewModel implements EditBookingContract.ViewModel {
    private EditBookingContract.Presenter mPresenter;

    public EditBookingViewModel() {
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
    public void setPresenter(EditBookingContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
