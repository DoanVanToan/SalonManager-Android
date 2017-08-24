package com.framgia.fsalon.screen.imagecustomer;

/**
 * Listens to user actions from the UI ({@link ImageCustomerFragment}), retrieves the data and
 * updates the UI as required.
 */
public class ImageCustomerPresenter implements ImageCustomerContract.Presenter {
    private static final String TAG = ImageCustomerPresenter.class.getName();
    private final ImageCustomerContract.ViewModel mViewModel;

    public ImageCustomerPresenter(ImageCustomerContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
