package com.framgia.fsalon.screen.user;

/**
 * Listens to user actions from the UI ({@link UserActivity}), retrieves the data and updates
 * the UI as required.
 */
final class UserPresenter implements UserContract.Presenter {
    private static final String TAG = UserPresenter.class.getName();
    private final UserContract.ViewModel mViewModel;

    public UserPresenter(UserContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
