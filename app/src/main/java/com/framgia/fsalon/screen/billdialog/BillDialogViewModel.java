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
import com.framgia.fsalon.screen.bill.BillViewModel;

/**
 * Exposes the data to be used in the BillDialog screen.
 */
public class BillDialogViewModel extends BaseObservable implements BillDialogContract.ViewModel {
    /**
     * Hander on click in diaglog
     */
    public interface OnClickDialogListener {
        void onDialogCancelClick(DialogFragment dialogFragment);
        void onDialogUpdateClick(BillDialogViewModel viewModel);
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
    private int stylistPos;
    private int servicePos;
    private Fragment mFragment;
    private OnClickDialogListener mOnClickDialogListener;

    public BillDialogViewModel(ArrayAdapter<Stylist> stylistAdapter,
                               ArrayAdapter<Service> serviceAdapter,
                               BillItemRequest itemRequest,
                               Fragment fragment,
                               OnClickDialogListener onClickDialogListener
    ) {
        mStylistAdapter = stylistAdapter;
        mServiceAdapter = serviceAdapter;
        mBillItemRequest = itemRequest;
        mPrice = String.valueOf(itemRequest.getPrice());
        mQty = String.valueOf(itemRequest.getQty());
        mTotal = itemRequest.getRowTotal();
        mFragment = fragment;
        mOnClickDialogListener = onClickDialogListener;
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

    public void onUpdateItem(BillViewModel billViewModel) {
        BillItemRequest bill = new BillItemRequest.Builder()
            .setServiceProductId(mService.getId())
            .setStylistId(mStylist.getId())
            .setPrice(Float.valueOf(mPrice))
            .setQty(Integer.valueOf(mQty))
            .setStylistName(mStylist.getName())
            .setServiceName(mService.getName())
            .create();
        billViewModel.onUpdateItemBill(bill, mBillItemRequest);
        ((DialogFragment) mFragment).dismiss();
    }

    public void onUpdateClick() {
        mOnClickDialogListener.onDialogUpdateClick(this);
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
        float total = 0;
        if (mQty != null && !mQty.isEmpty()) {
            total = Float.valueOf(mPrice) * Float.valueOf(mQty);
        } else {
            total = Float.valueOf(0);
        }
        setTotal(total);
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
    public BillItemRequest getBillItemRequest() {
        return mBillItemRequest;
    }

    public void setBillItemRequest(BillItemRequest billItemRequest) {
        mBillItemRequest = billItemRequest;
        notifyPropertyChanged(BR.billItemRequest);
    }

    @Bindable
    public int getStylistPos() {
        return stylistPos;
    }

    public void setStylistPos(int stylistPos) {
        this.stylistPos = stylistPos;
        notifyPropertyChanged(BR.stylistPos);
    }

    @Bindable
    public int getServicePos() {
        return servicePos;
    }

    public void setServicePos(int servicePos) {
        this.servicePos = servicePos;
        notifyPropertyChanged(BR.servicePos);
    }

    @Override
    public void setStylistPosition(int pos) {
        setStylistPos(pos);
    }

    @Override
    public void setServicePosition(int pos) {
        setServicePos(pos);
    }
}
