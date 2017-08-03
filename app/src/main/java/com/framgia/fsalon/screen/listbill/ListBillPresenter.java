package com.framgia.fsalon.screen.listbill;

/**
 * Listens to user actions from the UI ({@link ListBillFragment}), retrieves the data and updates
 * the UI as required.
 */
public class ListBillPresenter implements ListBillContract.Presenter {
    private static final String TAG = ListBillPresenter.class.getName();
    private final ListBillContract.ViewModel mViewModel;

    public ListBillPresenter(ListBillContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
