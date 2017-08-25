package com.framgia.fsalon.screen.homestylist;

/**
 * Listens to user actions from the UI ({@link HomeStylistActivity}), retrieves the data and updates
 * the UI as required.
 */
public class HomeStylistPresenter implements HomeStylistContract.Presenter {
    private static final String TAG = HomeStylistPresenter.class.getName();
    private final HomeStylistContract.ViewModel mViewModel;

    public HomeStylistPresenter(HomeStylistContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
