package com.framgia.fsalon.screen.success;

/**
 * Listens to user actions from the UI ({@link SuccessActivity}), retrieves the data and updates
 * the UI as required.
 */
final class SuccessPresenter implements SuccessContract.Presenter {
    private static final String TAG = SuccessPresenter.class.getName();
    private final SuccessContract.ViewModel mViewModel;

    public SuccessPresenter(SuccessContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
