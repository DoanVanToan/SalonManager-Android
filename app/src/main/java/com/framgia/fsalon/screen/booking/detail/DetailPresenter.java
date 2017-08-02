package com.framgia.fsalon.screen.booking.detail;

/**
 * Listens to user actions from the UI ({@link DetailFragment}), retrieves the data and updates
 * the UI as required.
 */
final class DetailPresenter implements DetailContract.Presenter {
    private static final String TAG = DetailPresenter.class.getName();
    private final DetailContract.ViewModel mViewModel;

    public DetailPresenter(DetailContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
