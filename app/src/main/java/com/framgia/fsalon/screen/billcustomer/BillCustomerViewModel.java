package com.framgia.fsalon.screen.billcustomer;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.data.model.ListBillRespond;
import com.framgia.fsalon.screen.billdetail.BillDetailActivity;
import com.framgia.fsalon.screen.listbill.ListBillAdapter;
import com.framgia.fsalon.utils.navigator.Navigator;
import com.framgia.fsalon.wiget.StickyHeaderLayoutManager;

import java.util.List;

/**
 * Exposes the data to be used in the Billcustomer screen.
 */
public class BillCustomerViewModel extends BaseObservable
    implements BillCustomerContract.ViewModel, ListBillAdapter.OnBillItemClick {
    private BillCustomerContract.Presenter mPresenter;
    private int mVisibleProgressBar;
    private StickyHeaderLayoutManager mLayoutManager = new StickyHeaderLayoutManager();
    private ListBillAdapter mAdapter;
    private Navigator mNavigator;
    private Context mContext;

    public BillCustomerViewModel(Fragment fragment) {
        mContext = fragment.getContext();
        mNavigator = new Navigator(fragment);
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
    public void setPresenter(BillCustomerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onHideProgressBar() {
        setVisibleProgressBar(View.GONE);
    }

    @Override
    public void onShowProgressBar() {
        setVisibleProgressBar(View.VISIBLE);
    }

    @Override
    public void onGetBillSuccessfully(List<ListBillRespond> listBillResponds) {
        setAdapter(new ListBillAdapter(listBillResponds, this));
    }

    @Override
    public void onError(String message) {
        mNavigator.showToast(message);
    }

    @Bindable
    public int getVisibleProgressBar() {
        return mVisibleProgressBar;
    }

    public void setVisibleProgressBar(int visibleProgressBar) {
        mVisibleProgressBar = visibleProgressBar;
        notifyPropertyChanged(BR.visibleProgressBar);
    }

    @Bindable
    public StickyHeaderLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public void setLayoutManager(StickyHeaderLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }

    @Bindable
    public ListBillAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ListBillAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void onBillDetailClick(BillResponse bill) {
        if (bill == null) {
            return;
        }
        mNavigator.startActivity(BillDetailActivity.getInstance(mContext, bill.getId()));
    }
}
