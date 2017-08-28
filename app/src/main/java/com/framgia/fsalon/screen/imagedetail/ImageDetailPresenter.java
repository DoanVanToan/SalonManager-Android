package com.framgia.fsalon.screen.imagedetail;

/**
 * Listens to user actions from the UI ({@link ImageDetailActivity}), retrieves the data and
 * updates the UI as required.
 */
public class ImageDetailPresenter implements ImageDetailContract.Presenter {
    private static final String TAG = ImageDetailPresenter.class.getName();
    private final ImageDetailContract.ViewModel mViewModel;

    public ImageDetailPresenter(ImageDetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
