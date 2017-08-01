package com.framgia.fsalon.screen.customer;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.FragmentActivity;

import com.framgia.fsalon.BR;

/**
 * Exposes the data to be used in the Customer screen.
 */
public class CustomerViewModel extends BaseObservable implements CustomerContract.ViewModel {
    private CustomerContract.Presenter mPresenter;
    private CustomerAdapter mCustomerAdapter;
    private FragmentActivity mActivity;
    private Context mContext;

    public CustomerViewModel(FragmentActivity activity) {
        mActivity = activity;
        mContext = mActivity.getApplicationContext();
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
    public void setPresenter(CustomerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public CustomerAdapter getCustomerAdapter() {
        return mCustomerAdapter;
    }

    public void setCustomerAdapter(CustomerAdapter customerAdapter) {
        mCustomerAdapter = customerAdapter;
        notifyPropertyChanged(BR.customerAdapter);
    }
}
