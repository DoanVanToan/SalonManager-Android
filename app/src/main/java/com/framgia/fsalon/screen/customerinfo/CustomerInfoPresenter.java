package com.framgia.fsalon.screen.customerinfo;

/**
 * Listens to user actions from the UI ({@link CustomerInfoActivity}),
 * retrieves the data and updates the UI as required.
 */
final class CustomerInfoPresenter implements CustomerInfoContract.Presenter {
    private static final String TAG = CustomerInfoPresenter.class.getName();
    private final CustomerInfoContract.ViewModel mViewModel;

    CustomerInfoPresenter(CustomerInfoContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
