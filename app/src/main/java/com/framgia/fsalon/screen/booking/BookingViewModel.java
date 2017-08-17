package com.framgia.fsalon.screen.booking;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingRender;
import com.framgia.fsalon.data.model.BookingResponse;
import com.framgia.fsalon.data.model.DateBooking;
import com.framgia.fsalon.data.model.Salon;
import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.screen.success.SuccessActivity;
import com.framgia.fsalon.utils.navigator.Navigator;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.framgia.fsalon.utils.Constant.BUNDLE_ORDER;
import static com.framgia.fsalon.utils.Constant.OUT_OF_INDEX;

/**
 * Exposes the data to be used in the Booking screen.
 */
public class BookingViewModel extends BaseObservable implements BookingContract.ViewModel {
    public static final int FIRST_ITEM = 0;
    private BookingContract.Presenter mPresenter;
    private SalonAdapter mSalonAdapter;
    private DateBookingAdapter mDateAdapter;
    private TimeBookingAdapter mTimeAdapter;
    private ArrayAdapter<Stylist> mStylistAdapter;
    private Context mContext;
    private long mTime = System.currentTimeMillis() / 1000;
    private int mSalonId;
    private int mStylistId = OUT_OF_INDEX;
    private String mPhone;
    private String mName;
    private int mRenderBookingId;
    private String mPhoneError;
    private String mNameError;
    private String mTimeError;
    private String mSalonError;
    private Navigator mNavigator;
    private boolean mIsEdit;
    private BookingOder mBookingOrder = null;
    private int mStylistPosition;

    public BookingViewModel(FragmentActivity activity) {
        mContext = activity.getApplicationContext();
        mNavigator = new Navigator(activity);
    }

    public BookingViewModel(FragmentActivity activity, BookingOder order) {
        this(activity);
        mBookingOrder = order;
        setName(order.getName());
        setPhone(order.getPhone());
        setEdit(true);
    }

    @Override
    public String getStringRes(int resId) {
        return mContext.getResources().getString(resId);
    }

    @Override
    public void onGetDateBookingSuccess(List<DateBooking> dateBookings) {
        setDateAdapter(new DateBookingAdapter(mContext, dateBookings, this));
        if (!isEdit()) {
            setDateAndTime(FIRST_ITEM, -1);
            return;
        }
        mPresenter.setDatePosition(mBookingOrder, dateBookings);
    }

    @Override
    public void setDateAndTime(int i, long millis) {
        mDateAdapter.selectedPosition(i);
        if (millis != -1) {
            mTime = millis;
        }
    }

    @Override
    public void onNoDate() {
        mNavigator.showToast(R.string.msg_cant_find_date);
    }

    @Override
    public void setStylist(int position) {
        setStylistPosition(position);
    }

    @Override
    public void onBookSuccess(BookingOder bookingOder) {
        if (!isEdit()) {
            mNavigator
                .startActivity(SuccessActivity.getInstance(mNavigator.getContext(), bookingOder));
        } else {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(BUNDLE_ORDER, bookingOder);
            mNavigator.finishActivityWithResult(resultIntent, RESULT_OK);
        }
    }

    @Override
    public void book() {
        mPresenter.book(mPhone, mName, mRenderBookingId, mStylistId);
    }

    @Override
    public void onInputPhoneError() {
        setPhoneError(getStringRes(R.string.msg_error_empty));
    }

    @Override
    public void onInputNameError() {
        setNameError(getStringRes(R.string.msg_error_empty));
    }

    @Override
    public void onInputTimeError() {
        if (mTimeAdapter == null || mTimeAdapter.getItemCount() <= 0) {
            onInputSalonError();
            return;
        }
        setTimeError(getStringRes(R.string.msg_pls_select_time));
    }

    @Override
    public void onInputSalonError() {
        setSalonError(getStringRes(R.string.msg_pls_select_salon));
    }

    @Override
    public void checkCustomer() {
        mPresenter.getCustomer();
    }

    @Override
    public void onCustomer(String name, String phone) {
        setName(name);
        setPhone(phone);
    }

    @Override
    public void onNotCustomer() {
        // TODO: 8/16/2017
    }

    @Override
    public void getData() {
        mPresenter.getBookings(mSalonId, mTime, mStylistId);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        if (!isEdit()) {
            checkCustomer();
        }
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(BookingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void selectedSalonPosition(int position, Salon salon) {
        if (salon == null) {
            return;
        }
        mSalonAdapter.selectedPosition(position);
        mSalonId = salon.getId();
        mPresenter.getAllStylists(mSalonId);
        getData();
    }

    @Override
    public void selectedDatePosition(int position, DateBooking dateBooking) {
        if (dateBooking == null) {
            return;
        }
        mDateAdapter.selectedPosition(position);
        mTime = dateBooking.getTimeMillis();
        getData();
    }

    @Override
    public void selectedTimePosition(int position, BookingRender bookingRender) {
        if (bookingRender == null) {
            return;
        }
        mTimeAdapter.selectedPosition(position);
        mRenderBookingId = bookingRender.getId();
    }

    @Override
    public void onError(String msg) {
        mNavigator.showToast(msg);
    }

    @Override
    public void showProgressbar() {
        // TODO: 24/07/2017
    }

    @Override
    public void hideProgressbar() {
        // TODO: 24/07/2017
    }

    @Override
    public void onGetSalonsSuccess(List<Salon> salons) {
        setSalonAdapter(new SalonAdapter(mContext, salons, this));
        if (isEdit()) {
            setStylistId(mBookingOrder.getStylistId());
            mPresenter.setSalonPosition(mBookingOrder, salons);
        }
    }

    @Override
    public void onGetStylistSuccess(List<Stylist> stylists) {
        setStylistAdapter(new ArrayAdapter<>(mContext, R.layout.item_spinner, stylists));
        if (isEdit()) {
            mPresenter.setStylist(mBookingOrder, stylists);
            return;
        }
        if (!stylists.isEmpty()) {
            setStylistId(stylists.get(FIRST_ITEM).getId());
        }
    }

    @Override
    public void onGetBookingSuccess(BookingResponse bookingResponse) {
        setTimeAdapter(new TimeBookingAdapter(mContext, bookingResponse.getRenders(), this));
        if (isEdit() && mBookingOrder.getStatus() != BookingOder.STATUS_IN_LATE) {
            mPresenter.setTimePosition(mBookingOrder, bookingResponse);
        }
    }

    @Bindable
    public SalonAdapter getSalonAdapter() {
        return mSalonAdapter;
    }

    public void setSalonAdapter(SalonAdapter salonAdapter) {
        mSalonAdapter = salonAdapter;
        notifyPropertyChanged(BR.salonAdapter);
    }

    @Bindable
    public DateBookingAdapter getDateAdapter() {
        return mDateAdapter;
    }

    public void setDateAdapter(DateBookingAdapter dateAdapter) {
        mDateAdapter = dateAdapter;
        notifyPropertyChanged(BR.dateAdapter);
    }

    @Bindable
    public TimeBookingAdapter getTimeAdapter() {
        return mTimeAdapter;
    }

    public void setTimeAdapter(TimeBookingAdapter timeAdapter) {
        mTimeAdapter = timeAdapter;
        notifyPropertyChanged(BR.timeAdapter);
    }

    @Bindable
    public ArrayAdapter<Stylist> getStylistAdapter() {
        return mStylistAdapter;
    }

    public void setStylistAdapter(ArrayAdapter<Stylist> stylistAdapter) {
        Stylist notRequire = new Stylist(mContext.getString(R.string.title_na));
        notRequire.setId(-1);
        stylistAdapter.add(notRequire);
        mStylistAdapter = stylistAdapter;
        notifyPropertyChanged(BR.stylistAdapter);
    }

    @Bindable
    public int getStylistId() {
        return mStylistId;
    }

    public void setStylistId(int stylistId) {
        mStylistId = stylistId;
        notifyPropertyChanged(BR.stylistId);
    }

    @Bindable
    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPhoneError() {
        return mPhoneError;
    }

    public void setPhoneError(String phoneError) {
        mPhoneError = phoneError;
        notifyPropertyChanged(BR.phoneError);
    }

    @Bindable
    public String getNameError() {
        return mNameError;
    }

    public void setNameError(String nameError) {
        mNameError = nameError;
        notifyPropertyChanged(BR.nameError);
    }

    @Bindable
    public String getTimeError() {
        return mTimeError;
    }

    public void setTimeError(String timeError) {
        mTimeError = timeError;
        notifyPropertyChanged(BR.timeError);
    }

    @Bindable
    public String getSalonError() {
        return mSalonError;
    }

    public void setSalonError(String salonError) {
        mSalonError = salonError;
        notifyPropertyChanged(BR.salonError);
    }

    @Bindable
    public boolean isEdit() {
        return mIsEdit;
    }

    public void setEdit(boolean edit) {
        mIsEdit = edit;
        notifyPropertyChanged(BR.edit);
    }

    @Bindable
    public BookingOder getBookingOrder() {
        return mBookingOrder;
    }

    public void setBookingOrder(BookingOder bookingOrder) {
        mBookingOrder = bookingOrder;
        notifyPropertyChanged(BR.bookingOder);
    }

    @Bindable
    public int getStylistPosition() {
        return mStylistPosition;
    }

    public void setStylistPosition(int stylistPosition) {
        mStylistPosition = stylistPosition;
        notifyPropertyChanged(BR.stylistPosition);
    }
}
