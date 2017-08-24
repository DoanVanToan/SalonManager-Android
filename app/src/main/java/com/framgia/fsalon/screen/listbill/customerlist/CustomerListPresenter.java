package com.framgia.fsalon.screen.listbill.customerlist;

/**
 * Listens to user actions from the UI ({@link CustomerListActivity}), retrieves
 * the data and updates the UI as required.
 */
public class CustomerListPresenter implements CustomerListContract.Presenter {
    private static final String TAG = CustomerListPresenter.class.getName();
    private final CustomerListContract.ViewModel mViewModel;

    public CustomerListPresenter(CustomerListContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
