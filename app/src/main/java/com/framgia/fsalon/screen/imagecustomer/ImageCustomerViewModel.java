package com.framgia.fsalon.screen.imagecustomer;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.codewaves.stickyheadergrid.StickyHeaderGridLayoutManager;
import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.utils.navigator.Navigator;

import java.util.List;

/**
 * Exposes the data to be used in the Imagecustomer screen.
 */
public class ImageCustomerViewModel extends BaseObservable
    implements ImageCustomerContract.ViewModel, ImageCustomerAdapter.OnImageItemClick {
    private static final int SPAN_COUNT = 3;
    private ImageCustomerContract.Presenter mPresenter;
    private Navigator mNavigator;
    private int mVisibleProgressBar;
    private StickyHeaderGridLayoutManager mLayoutManager =
        new StickyHeaderGridLayoutManager(SPAN_COUNT);
    private ImageCustomerAdapter mAdapter;

    public ImageCustomerViewModel(Fragment fragment) {
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
    public void setPresenter(ImageCustomerContract.Presenter presenter) {
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
    public void onError(String message) {
        mNavigator.showToast(message);
    }

    @Override
    public void onGetBillSuccessfully(List<BillResponse> billResponses) {
        setAdapter(new ImageCustomerAdapter(billResponses, this));
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
    public StickyHeaderGridLayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public void setLayoutManager(StickyHeaderGridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }

    @Bindable
    public ImageCustomerAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(ImageCustomerAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}
