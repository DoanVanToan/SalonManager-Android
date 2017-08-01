package com.framgia.fsalon.screen.booking;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.design.widget.Snackbar;
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

import static com.framgia.fsalon.utils.Constant.OUT_OF_INDEX;

/**
 * Exposes the data to be used in the Booking screen.
 */
public class BookingViewModel extends BaseObservable implements BookingContract.ViewModel {
    private static final int FIRST_ITEM = 0;
    private BookingContract.Presenter mPresenter;
    private SalonAdapter mSalonAdapter;
    private DateBookingAdapter mDateAdapter;
    private TimeBookingAdapter mTimeAdapter;
    private ArrayAdapter<Stylist> mStylistAdapter;
    private FragmentActivity mActivity;
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

    public BookingViewModel(FragmentActivity activity) {
        mActivity = activity;
        mContext = activity.getApplicationContext();
        mNavigator = new Navigator(mActivity);
    }

    @Override
    public String getStringRes(int resId) {
        return mContext.getResources().getString(resId);
    }

    @Override
    public void onGetDateBookingSuccess(List<DateBooking> dateBookings) {
        setDateAdapter(new DateBookingAdapter(mContext, dateBookings, this));
        mDateAdapter.selectedPosition(FIRST_ITEM);
    }

    @Override
    public void onBookSuccess(BookingOder bookingOder) {
        mNavigator.startActivity(SuccessActivity.getInstance(mNavigator.getContext(), bookingOder));
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
    public void getData() {
        mPresenter.getBookings(mSalonId, mTime, mStylistId);
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
        Snackbar.make(mActivity.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
            .show();
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
    }

    @Override
    public void onGetStylistSuccess(List<Stylist> stylists) {
        setStylistAdapter(new ArrayAdapter<>(mContext, R.layout.item_spinner, stylists));
    }

    @Override
    public void onGetBookingSuccess(BookingResponse bookingResponse) {
        setTimeAdapter(new TimeBookingAdapter(mContext, bookingResponse.getRenders(), this));
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
}
