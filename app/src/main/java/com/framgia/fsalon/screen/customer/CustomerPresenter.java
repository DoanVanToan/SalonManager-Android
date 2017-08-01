package com.framgia.fsalon.screen.customer;

/**
 * Listens to user actions from the UI ({@link CustomerFragment}), retrieves the data and updates
 * the UI as required.
 */
final class CustomerPresenter implements CustomerContract.Presenter {
    private static final String TAG = CustomerPresenter.class.getName();
    private final CustomerContract.ViewModel mViewModel;

    public CustomerPresenter(CustomerContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
