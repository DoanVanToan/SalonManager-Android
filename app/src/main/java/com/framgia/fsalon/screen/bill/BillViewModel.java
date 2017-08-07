package com.framgia.fsalon.screen.bill;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.ArrayAdapter;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BillItemRequest;
import com.framgia.fsalon.data.model.BillRequest;
import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.Salon;
import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.utils.navigator.Navigator;

import java.util.ArrayList;
import java.util.List;

import static com.framgia.fsalon.utils.Constant.Status.STATUS_PENDING;

/**
 * Exposes the data to be used in the BillItemRequest screen.
 */
public class BillViewModel extends BaseObservable implements BillContract.ViewModel {
    private static final String FIRST_ITEM = "1";
    private BillContract.Presenter mPresenter;
    private BillRequest mBillRequest = new BillRequest();
    private ArrayAdapter<Stylist> mStylistAdapter;
    private ArrayAdapter<Service> mServiceAdapter;
    private ArrayAdapter<Salon> mSalonAdapter;
    private BillAdapter mAdapter;
    private Stylist mStylist;
    private Service mService;
    private String mPrice;
    private String mQty;
    private Context mContext;
    private float mTotal;
    private Navigator mNavigator;
    private String mFormError;
    private String mCustomerNameError;
    private String mCustomerPhoneError;
    private Salon mSalon;

    public BillViewModel(Activity activity) {
        mContext = activity.getApplicationContext();
        mAdapter = new BillAdapter(mContext, new ArrayList<BillItemRequest>(), this);
        mNavigator = new Navigator(activity);
        mBillRequest.setOrderBookingId(-1);
        setQty(FIRST_ITEM);
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
        if (!mPresenter.validateFormInput(mService, mStylist, mSalon, mPrice, mQty, mBillRequest
            .getPhone(), mBillRequest.getCustomerName())) {
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
        mAdapter.onUpdateAdapter(bill);
        setTotal(mAdapter.getTotalPrice());
    }

    @Override
    public void showProgressbar() {
        // TODO: 02/08/2017  
    }

    @Override
    public void onGetStylistSuccess(List<Stylist> stylists) {
        setStylistAdapter(new ArrayAdapter<>(mContext, R.layout.item_spinner_small, stylists));
    }

    @Override
    public void onGetSalonsSuccess(List<Salon> salons) {
        setSalonAdapter(new ArrayAdapter<>(mContext, R.layout.item_spinner_small, salons));
    }

    @Override
    public void hideProgressbar() {
        // TODO: 02/08/2017  
    }

    @Override
    public void onError(String message) {
        mNavigator.showToast(message);
    }

    @Override
    public void onGetServiceSuccess(List<Service> services) {
        setServiceAdapter(new ArrayAdapter<>(mContext, R.layout.item_spinner_small, services));
    }

    @Override
    public void onDeleteItemClick(int position) {
        mAdapter.onDeleteItem(position);
        setTotal(mAdapter.getTotalPrice());
    }

    @Override
    public void onGetBillSuccess(BillResponse billResponse) {
        mNavigator.finishActivity();
    }

    @Override
    public void onCreateBillClick() {
        mBillRequest.setBillItems(mAdapter.getData());
        mBillRequest.setGrandTotal(mTotal);
        mBillRequest.setStatus(STATUS_PENDING);
        mPresenter.createBill(mBillRequest);
    }

    @Override
    public void onGetBookingSuccess(BookingOder bookingOder) {
        mBillRequest.setCustomerName(bookingOder.getName());
        mBillRequest.setCustomerId(bookingOder.getUserId());
        mBillRequest.setOrderBookingId(bookingOder.getId());
        mBillRequest.setPhone(bookingOder.getPhone());
    }

    @Override
    public void onFilterPhone() {
        mPresenter.getBookingByPhone(mBillRequest.getPhone());
    }

    @Override
    public void onInputFormError() {
        setFormError(mNavigator.getStringById(R.string.msg_error_form));
    }

    @Override
    public void onInputCustomerNameError() {
        setCustomerNameError(mNavigator.getStringById(R.string.msg_error_empty));
    }

    @Override
    public void onInputCustomerPhoneError() {
        setCustomerPhoneError(mNavigator.getStringById(R.string.msg_error_empty));
    }

    @Override
    public void getCustomerSuccessfull(User user) {
        mBillRequest.setCustomerName(user.getName());
        mBillRequest.setCustomerId(user.getId());
        mBillRequest.setPhone(user.getPhone());
    }

    @Override
    public void onHideCustomerPhoneError() {
        setCustomerPhoneError(null);
    }

    @Override
    public void onHideCustomer() {
        mBillRequest.setCustomerName(null);
        mBillRequest.setOrderBookingId(-1);
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

    @Bindable
    public float getTotal() {
        return mTotal;
    }

    public void setTotal(float total) {
        mTotal = total;
        notifyPropertyChanged(BR.total);
    }

    @Bindable
    public String getFormError() {
        return mFormError;
    }

    public void setFormError(String formError) {
        mFormError = formError;
        notifyPropertyChanged(BR.formError);
    }

    @Bindable
    public String getCustomerNameError() {
        return mCustomerNameError;
    }

    public void setCustomerNameError(String customerNameError) {
        mCustomerNameError = customerNameError;
        notifyPropertyChanged(BR.customerNameError);
    }

    @Bindable
    public String getCustomerPhoneError() {
        return mCustomerPhoneError;
    }

    public void setCustomerPhoneError(String customerPhoneError) {
        mCustomerPhoneError = customerPhoneError;
        notifyPropertyChanged(BR.customerPhoneError);
    }

    @Bindable
    public ArrayAdapter<Salon> getSalonAdapter() {
        return mSalonAdapter;
    }

    public void setSalonAdapter(ArrayAdapter<Salon> salonAdapter) {
        mSalonAdapter = salonAdapter;
        notifyPropertyChanged(BR.salonAdapter);
    }

    @Bindable
    public Salon getSalon() {
        return mSalon;
    }

    public void setSalon(Salon salon) {
        mSalon = salon;
        notifyPropertyChanged(BR.salon);
    }
}
