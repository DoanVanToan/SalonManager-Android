package com.framgia.fsalon.screen.billdialog;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.data.model.BillItemRequest;
import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.screen.bill.BillAdapter;

/**
 * Exposes the data to be used in the BillDialog screen.
 */
public class BillDialogViewModel extends BaseObservable implements BillDialogContract.ViewModel {
    /**
     * Hander on click in diaglog
     */
    public interface OnClickDialogListener {
        void onDialogCancelClick(DialogFragment dialogFragment);
        void onDialogUpdateClick(BillItemRequest newBill, BillItemRequest oldBill);
    }

    private BillDialogContract.Presenter mPresenter;
    private ArrayAdapter<Stylist> mStylistAdapter;
    private ArrayAdapter<Service> mServiceAdapter;
    private BillAdapter mAdapter;
    private Stylist mStylist;
    private Service mService;
    private String mPrice;
    private String mQty;
    private float mTotal;
    private BillItemRequest mBillItemRequest;
    private int mStylistPos;
    private int mServicePos;
    private Fragment mFragment;
    private OnClickDialogListener mOnClickDialogListener;

    public BillDialogViewModel(ArrayAdapter<Stylist> stylistAdapter,
                               ArrayAdapter<Service> serviceAdapter, BillItemRequest itemRequest,
                               Fragment fragment, OnClickDialogListener onClickDialogListener) {
        mStylistAdapter = stylistAdapter;
        mServiceAdapter = serviceAdapter;
        mBillItemRequest = itemRequest;
        mPrice = String.valueOf(itemRequest.getPrice());
        mQty = String.valueOf(itemRequest.getQty());
        mTotal = itemRequest.getRowTotal();
        mFragment = fragment;
        mOnClickDialogListener = onClickDialogListener;
        setService(new Service(itemRequest.getServiceProductId()));
        setStylist(new Stylist(itemRequest.getStylistId()));
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    public void onCancelClick() {
        mOnClickDialogListener.onDialogCancelClick((DialogFragment) mFragment);
    }

    public void onUpdateClick() {
        BillItemRequest bill = new BillItemRequest.Builder()
            .setServiceProductId(mService.getId())
            .setStylistId(mStylist.getId())
            .setPrice(Float.valueOf(mPrice))
            .setQty(Integer.valueOf(mQty))
            .setStylistName(mStylist.getName())
            .setServiceName(mService.getName())
            .create();
        mOnClickDialogListener.onDialogUpdateClick(bill, mBillItemRequest);
        ((DialogFragment) mFragment).dismiss();
    }

    @Override
    public void setPresenter(BillDialogContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void onGetPrice(String price) {
        setPrice(price);
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

    public void setServiceAdapter(ArrayAdapter<Service> serviceAdapter) {
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
        updateTotal();
    }

    @Bindable
    public String getQty() {
        return mQty;
    }

    public void setQty(String qty) {
        mQty = qty;
        notifyPropertyChanged(BR.qty);
        updateTotal();
    }

    public void updateTotal() {
        if (mQty != null && !mQty.isEmpty()) {
            setTotal(Float.valueOf(mPrice) * Float.valueOf(mQty));
        } else {
            setTotal(0);
        }
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
    public int getStylistPos() {
        return mStylistPos;
    }

    public void setStylistPos(int stylistPos) {
        mStylistPos = stylistPos;
        notifyPropertyChanged(BR.stylistPos);
    }

    @Bindable
    public int getServicePos() {
        return mServicePos;
    }

    public void setServicePos(int servicePos) {
        mServicePos = servicePos;
        notifyPropertyChanged(BR.servicePos);
    }
}
