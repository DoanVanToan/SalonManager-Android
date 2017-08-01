package com.framgia.fsalon.screen.register;

/**
 * Listens to user actions from the UI ({@link RegisterActivity}), retrieves the data and updates
 * the UI as required.
 */
final class RegisterPresenter implements RegisterContract.Presenter {
    private final RegisterContract.ViewModel mViewModel;

    public RegisterPresenter(RegisterContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
