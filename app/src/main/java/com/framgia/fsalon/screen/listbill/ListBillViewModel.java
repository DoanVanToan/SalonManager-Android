package com.framgia.fsalon.screen.listbill;

import android.app.Activity;
import android.content.Context;

import com.framgia.fsalon.screen.bill.BillActivity;
import com.framgia.fsalon.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Listbill screen.
 */
public class ListBillViewModel implements ListBillContract.ViewModel {
    private ListBillContract.Presenter mPresenter;
    private Activity mActivity;
    private Context mContext;
    private Navigator mNavigator;

    public ListBillViewModel(Activity activity) {
        mActivity = activity;
        mContext = activity.getApplicationContext();
        mNavigator = new Navigator(mActivity);
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
    public void setPresenter(ListBillContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCreateBillClick() {
        mNavigator.startActivity(BillActivity.getInstance(mContext));
    }
}
