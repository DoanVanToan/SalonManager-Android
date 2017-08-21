package com.framgia.fsalon.screen.billdetail;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.data.model.BillItemResponse;
import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.screen.bill.BillActivity;
import com.framgia.fsalon.utils.navigator.Navigator;

import java.util.ArrayList;

/**
 * Exposes the data to be used in the Billdetail screen.
 */
public class BillDetailViewModel extends BaseObservable implements BillDetailContract.ViewModel {
    private BillDetailContract.Presenter mPresenter;
    private float mTotal;
    private BillDetailAdapter mAdapter;
    private Activity mActivity;
    private Context mContext;
    private Navigator mNavigator;
    private BillResponse mBillResponse;
    private int mProgressBarVisibility;

    public BillDetailViewModel(Activity activity) {
        mContext = activity.getApplicationContext();
        mNavigator = new Navigator(activity);
        mAdapter = new BillDetailAdapter(mContext, new ArrayList<BillItemResponse>(), this);
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
    public void setPresenter(BillDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public float getTotal() {
        return mTotal;
    }

    public void setTotal(float total) {
        mTotal = total;
        notifyPropertyChanged(BR.total);
    }

    @Bindable
    public BillDetailAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BillDetailAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Override
    public void showProgressbar() {
        setProgressBarVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        setProgressBarVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        mNavigator.showToast(message);
    }

    @Override
    public void onGetBillSuccess(BillResponse billResponse) {
        setBillResponse(billResponse);
        mAdapter.onUpdatePage(billResponse.getBillItems());
    }

    @Override
    public void onEditBillClick() {
        mNavigator.startActivity(BillActivity.getInstance(mNavigator.getContext(),
            mBillResponse.getId()));
    }

    @Bindable
    public BillResponse getBillResponse() {
        return mBillResponse;
    }

    public void setBillResponse(BillResponse billResponse) {
        mBillResponse = billResponse;
        notifyPropertyChanged(BR.billResponse);
    }

    @Bindable
    public int getProgressBarVisibility() {
        return mProgressBarVisibility;
    }

    public void setProgressBarVisibility(int progressBarVisibility) {
        mProgressBarVisibility = progressBarVisibility;
        notifyPropertyChanged(BR.progressBarVisibility);
    }
}
