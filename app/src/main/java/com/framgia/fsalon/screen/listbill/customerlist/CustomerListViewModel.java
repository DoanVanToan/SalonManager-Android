package com.framgia.fsalon.screen.listbill.customerlist;

/**
 * Exposes the data to be used in the CustomerList screen.
 */
public class CustomerListViewModel implements CustomerListContract.ViewModel {
    private CustomerListContract.Presenter mPresenter;

    public CustomerListViewModel() {
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(CustomerListContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
