package com.framgia.fsalon.screen.bill;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.widget.ArrayAdapter;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BillItemRequest;
import com.framgia.fsalon.data.model.BillRequest;
import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.Stylist;

import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the BillItemRequest screen.
 */
public class BillViewModel extends BaseObservable implements BillContract.ViewModel {
    private BillContract.Presenter mPresenter;
    private BillRequest mBillRequest;
    private ArrayAdapter<Stylist> mStylistAdapter;
    private ArrayAdapter<Service> mServiceAdapter;
    private BillAdapter mAdapter;
    private Stylist mStylist;
    private Service mService;
    private String mPrice;
    private String mQty;
    private Activity mActivity;
    private Context mContext;

    public BillViewModel(Activity activity) {
        mActivity = activity;
        mContext = activity.getApplicationContext();
        mAdapter = new BillAdapter(mContext, new ArrayList<BillItemRequest>(), this);
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
    public void setPresenter(BillContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onAddBillClick() {
        if (mService == null || mStylist == null || TextUtils.isEmpty(mPrice) || TextUtils
            .isEmpty(mQty)) {
            return;
        }
        BillItemRequest bill = new BillItemRequest.Builder()
            .setId(mService.getId())
            .setStylistId(mStylist.getId())
            .setPrice(Float.valueOf(mPrice))
            .setQty(Integer.valueOf(mQty))
            .setStylistName(mStylist.getName())
            .setServiceName(mService.getName())
            .create();
        mAdapter.onAddItem(bill);
    }

    @Override
    public void showProgressbar() {
        // TODO: 02/08/2017  
    }

    @Override
    public void onGetStylistSuccess(List<Stylist> stylists) {
        setStylistAdapter(new ArrayAdapter<>(mContext, R.layout.item_spinner, stylists));
    }

    @Override
    public void hideProgressbar() {
        // TODO: 02/08/2017  
    }

    @Override
    public void onError(String message) {
        // TODO: 02/08/2017  
    }

    @Override
    public void onGetServiceSuccess(List<Service> services) {
        setServiceAdapter(new ArrayAdapter<>(mContext, R.layout.item_spinner, services));
    }

    @Override
    public void onDeleteItemClick(int position) {
        mAdapter.onDeleteItem(position);
    }

    public void onGetPrice(String price) {
        setPrice(price);
    }

    @Bindable
    public BillRequest getBillRequest() {
        return mBillRequest;
    }

    public void setBillRequest(BillRequest billRequest) {
        mBillRequest = billRequest;
        notifyPropertyChanged(BR.billRequest);
    }

    @Bindable
    public ArrayAdapter<Stylist> getStylistAdapter() {
        return mStylistAdapter;
    }

    public void setStylistAdapter(
        ArrayAdapter<Stylist> stylistAdapter) {
        mStylistAdapter = stylistAdapter;
        notifyPropertyChanged(BR.stylistAdapter);
    }

    @Bindable
    public ArrayAdapter<Service> getServiceAdapter() {
        return mServiceAdapter;
    }

    public void setServiceAdapter(
        ArrayAdapter<Service> serviceAdapter) {
        mServiceAdapter = serviceAdapter;
        notifyPropertyChanged(BR.serviceAdapter);
    }

    @Bindable
    public Stylist getStylist() {
        return mStylist;
    }

    public void setStylist(Stylist stylist) {
        mStylist = stylist;
        notifyPropertyChanged(BR.stylist);
    }

    @Bindable
    public Service getService() {
        return mService;
    }

    public void setService(Service service) {
        mService = service;
        onGetPrice(String.valueOf(service.getPrice()));
        notifyPropertyChanged(BR.service);
    }

    @Bindable
    public BillAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BillAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Bindable
    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getQty() {
        return mQty;
    }

    public void setQty(String qty) {
        mQty = qty;
        notifyPropertyChanged(BR.qty);
    }
}
