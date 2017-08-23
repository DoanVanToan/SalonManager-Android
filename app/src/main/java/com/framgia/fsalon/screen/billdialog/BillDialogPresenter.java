package com.framgia.fsalon.screen.billdialog;

/**
 * Listens to user actions from the UI ({@link BillDialogFragment}), retrieves the data and updates
 * the UI as required.
 */
public class BillDialogPresenter implements BillDialogContract.Presenter {
    private final BillDialogContract.ViewModel mViewModel;

    public BillDialogPresenter(BillDialogContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
