package com.framgia.fsalon.screen.register;

/**
 * Exposes the data to be used in the Register screen.
 */
public class RegisterViewModel implements RegisterContract.ViewModel {
    private RegisterContract.Presenter mPresenter;

    public RegisterViewModel() {
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
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
