package com.framgia.fsalon.screen.customerinfo.user;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Listens to user actions from the UI ({@link UserFragment}), retrieves the data and updates
 * the UI as required.
 */
final class UserPresenter implements UserContract.Presenter {
    private static final String TAG = UserPresenter.class.getName();
    private final UserContract.ViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    UserPresenter(UserContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }
}
