package com.framgia.fsalon.screen.homeadmin;

/**
 * Listens to user actions from the UI ({@link AdminHomeActivity}), retrieves the data and updates
 * the UI as required.
 */
final class AdminHomePresenter implements AdminHomeContract.Presenter {
    private static final String TAG = AdminHomePresenter.class.getName();
    private final AdminHomeContract.ViewModel mViewModel;

    public AdminHomePresenter(AdminHomeContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
