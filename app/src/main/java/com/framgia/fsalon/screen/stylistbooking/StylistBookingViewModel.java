package com.framgia.fsalon.screen.stylistbooking;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.framgia.fsalon.FSalonApplication;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.ManageBookingResponse;
import com.framgia.fsalon.screen.scheduler.SchedulerAdapter;
import com.framgia.fsalon.screen.scheduler.detail.BookingDetailActivity;
import com.framgia.fsalon.utils.Utils;
import com.framgia.fsalon.utils.navigator.Navigator;
import com.framgia.fsalon.wiget.StickyHeaderLayoutManager;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_CANCELED;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_FINISHED;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_IN_LATE;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_IN_PROGRESS;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_WATTING;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_TODAY;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_TOMORROW;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_YESTERDAY;
import static com.framgia.fsalon.utils.Constant.OUT_OF_INDEX;

/**
 * Exposes the data to be used in the StylistBooking screen.
 */
public class StylistBookingViewModel extends BaseObservable
    implements StylistBookingContract.ViewModel, DatePickerDialog.OnDateSetListener,
    DialogInterface.OnCancelListener, SchedulerAdapter.BookingDetailListener {
    private StylistBookingContract.Presenter mPresenter;
    private SchedulerAdapter mDateAdapter;
    private int mStartDate = OUT_OF_INDEX;
    private String mStatusId = "";
    private boolean mIsWatting = true;
    private boolean mIsFinished = true;
    private boolean mIsCanceled = true;
    private boolean mIsInLate = true;
    private boolean mIsInProgress = true;
    private String mTitleDate;
    private int mRadioButtonId;
    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendar;
    private FragmentManager mFragmentManager;
    private int mVisibleProgressBar = GONE;
    private StickyHeaderLayoutManager mLayoutManager = new StickyHeaderLayoutManager();
    private Navigator mNavigator;
    private DrawerLayout.DrawerListener mDrawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerOpened(View drawerView) {
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            makeStatusFilter();
            mPresenter
                .filterBookings(mStartDate, mStatusId);
        }

        @Override
        public void onDrawerStateChanged(int newState) {
        }
    };

    private void makeStatusFilter() {
        mStatusId = "";
        if (mIsCanceled) {
            mStatusId += STATUS_CANCELED + ",";
        }
        if (mIsWatting) {
            mStatusId += STATUS_WATTING + ",";
        }
        if (mIsFinished) {
            mStatusId += STATUS_FINISHED + ",";
        }
        if (mIsInLate) {
            mStatusId += STATUS_IN_LATE + ",";
        }
        if (mIsInProgress) {
            mStatusId += STATUS_IN_PROGRESS + ",";
        }
        mStatusId = mStatusId.substring(0, mStatusId.length() - 1);
    }

    private RadioGroup.OnCheckedChangeListener mChangeListener = new RadioGroup
        .OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.filter_today:
                    mStartDate = Utils.createTimeStamp(TAB_TODAY);
                    setTitleDate(FSalonApplication.getInstant().getResources()
                        .getString(R.string.title_today));
                    setRadioButtonId(R.id.filter_today);
                    break;
                case R.id.filter_yesterday:
                    mStartDate = Utils.createTimeStamp(TAB_YESTERDAY);
                    setTitleDate(FSalonApplication.getInstant().getResources()
                        .getString(R.string.title_yesterday));
                    setRadioButtonId(R.id.filter_yesterday);
                    break;
                case R.id.filter_tomorrow:
                    mStartDate = Utils.createTimeStamp(TAB_TOMORROW);
                    setTitleDate(FSalonApplication.getInstant().getResources()
                        .getString(R.string.title_tomorrow));
                    setRadioButtonId(R.id.filter_tomorrow);
                    break;
                case R.id.filter_select_date:
                    setTitleDate(FSalonApplication.getInstant().getResources()
                        .getString(R.string.title_select_date));
                    break;
                default:
                    break;
            }
        }
    };

    public StylistBookingViewModel(Fragment fragment) {
        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }
        mNavigator = new Navigator(fragment);
        mFragmentManager = fragment.getActivity().getFragmentManager();
        mDatePickerDialog =
            DatePickerDialog.newInstance(this, mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.setOnDateSetListener(this);
        mDatePickerDialog.setOnCancelListener(this);
        setTitleDate(FSalonApplication.getInstant().getResources()
            .getString(R.string.action_pick_date));
        setRadioButtonId(R.id.filter_today);
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
    public void setPresenter(StylistBookingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public SchedulerAdapter getDateAdapter() {
        return mDateAdapter;
    }

    public void setDateAdapter(SchedulerAdapter dateAdapter) {
        mDateAdapter = dateAdapter;
        notifyPropertyChanged(BR.dateAdapter);
    }

    @Bindable
    public String getTitleDate() {
        return mTitleDate;
    }

    public void setTitleDate(String titleDate) {
        mTitleDate = titleDate;
        notifyPropertyChanged(BR.titleDate);
    }

    @Bindable
    public int getRadioButtonId() {
        return mRadioButtonId;
    }

    public void setRadioButtonId(int radioButtonId) {
        mRadioButtonId = radioButtonId;
        notifyPropertyChanged(BR.radioButtonId);
    }

    @Bindable
    public RadioGroup.OnCheckedChangeListener getChangeListener() {
        return mChangeListener;
    }

    public void setChangeListener(RadioGroup.OnCheckedChangeListener changeListener) {
        mChangeListener = changeListener;
        notifyPropertyChanged(BR.changeListener);
    }

    @Bindable
    public DrawerLayout.DrawerListener getDrawerListener() {
        return mDrawerListener;
    }

    public void setDrawerListener(DrawerLayout.DrawerListener drawerListener) {
        mDrawerListener = drawerListener;
        notifyPropertyChanged(BR.drawerListener);
    }

    @Bindable
    public boolean isWatting() {
        return mIsWatting;
    }

    public void setWatting(boolean watting) {
        mIsWatting = watting;
        notifyPropertyChanged(BR.waiting);
    }

    @Bindable
    public boolean isFinished() {
        return mIsFinished;
    }

    public void setFinished(boolean finished) {
        mIsFinished = finished;
        notifyPropertyChanged(BR.finished);
    }

    @Bindable
    public boolean isCanceled() {
        return mIsCanceled;
    }

    public void setCanceled(boolean canceled) {
        mIsCanceled = canceled;
        notifyPropertyChanged(BR.canceled);
    }

    @Bindable
    public boolean isInLate() {
        return mIsInLate;
    }

    public void setInLate(boolean inLate) {
        mIsInLate = inLate;
        notifyPropertyChanged(BR.inLate);
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
    public boolean isInProgress() {
        return mIsInProgress;
    }

    public void setInProgress(boolean inProgress) {
        mIsInProgress = inProgress;
        notifyPropertyChanged(BR.inProgress);
    }

    @Override
    public void onSelectDateClick() {
        showDatePickerDialog();
    }

    @Override
    public void onFilterClick(DrawerLayout layout) {
        if (layout.isDrawerOpen(Gravity.START)) {
            layout.closeDrawer(Gravity.START);
            return;
        }
        layout.openDrawer(Gravity.START);
    }

    @Override
    public void showLoadMore() {
        setVisibleProgressBar(VISIBLE);
    }

    @Override
    public void onFilterSuccessfully(List<ManageBookingResponse> manageBookingResponse) {
        setDateAdapter(new SchedulerAdapter(this, manageBookingResponse));
        hideLoadMore();
    }

    @Override
    public void onFilterError() {
        hideLoadMore();
    }

    @Override
    public void hideLoadMore() {
        setVisibleProgressBar(GONE);
    }

    private void showDatePickerDialog() {
        mDatePickerDialog.setTitle(FSalonApplication.getInstant().getResources()
            .getString(R.string.title_select_date));
        mDatePickerDialog.show(mFragmentManager, "");
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        setRadioButtonId(mRadioButtonId);
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        if (mDatePickerDialog != null) {
            mDatePickerDialog.dismiss();
        }
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, monthOfYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        mCalendar.set(Calendar.HOUR_OF_DAY, 0);
        mCalendar.set(Calendar.MINUTE, 0);
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);
        mStartDate = (int) (mCalendar.getTimeInMillis() / 1000);
        showDatePickerDialog();
    }

    @Override
    public void onBookingItemClick(int id) {
        mNavigator.startActivity(BookingDetailActivity.getInstance(mNavigator.getContext(), id));
    }
}
