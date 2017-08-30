package com.framgia.fsalon.screen.editcustomerinfo;

/**
 * Listens to user actions from the UI
 * ({@link EditcustomerinfoActivity}), retrieves the data and updates
 * the UI as required.
 */
public class EditcustomerinfoPresenter implements EditcustomerinfoContract.Presenter {
    private final EditcustomerinfoContract.ViewModel mViewModel;

    public EditcustomerinfoPresenter(EditcustomerinfoContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
