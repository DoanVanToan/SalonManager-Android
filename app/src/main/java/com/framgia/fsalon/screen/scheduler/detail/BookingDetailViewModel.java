package com.framgia.fsalon.screen.scheduler.detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.Status;
import com.framgia.fsalon.screen.editbooking.EditBookingActivity;
import com.framgia.fsalon.screen.editstatusdialog.EditStatusDialogFragment;
import com.framgia.fsalon.utils.Constant;
import com.framgia.fsalon.utils.Utils;
import com.framgia.fsalon.utils.navigator.Navigator;
import com.github.clans.fab.FloatingActionMenu;

/**
 * Exposes the data to be used in the Detail screen.
 */
public class BookingDetailViewModel extends BaseObservable
    implements BookingDetailContract.ViewModel {
    private static final String EDIT_STATUS_DIALOG = "EDIT_STATUS_DIALOG";
    private BookingDetailContract.Presenter mPresenter;
    private BookingOder mBookingOder;
    private int mId;
    private int mProgressBarVisibility;
    private boolean mIsFinish = true;
    private Navigator mNavigator;
    private boolean mIsSelected;
    private boolean mIsChangeStatus;
    private AppCompatActivity mActivity;
    private SwipeRefreshLayout.OnRefreshListener mListener =
        new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getBookingById(mId);
            }
        };

    public BookingDetailViewModel(AppCompatActivity activity, int id) {
        mActivity = activity;
        mNavigator = new Navigator(activity);
        mId = id;
    }

    public SwipeRefreshLayout.OnRefreshListener getListener() {
        return mListener;
    }

    public void setListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mListener = listener;
    }

    @Bindable
    public BookingOder getBookingOder() {
        return mBookingOder;
    }

    public void setBookingOder(BookingOder bookingOder) {
        mBookingOder = bookingOder;
        notifyPropertyChanged(BR.bookingOder);
    }

    @Bindable
    public int getProgressBarVisibility() {
        return mProgressBarVisibility;
    }

    public void setProgressBarVisibility(int progressBarVisibility) {
        mProgressBarVisibility = progressBarVisibility;
        notifyPropertyChanged(BR.progressBarVisibility);
    }

    @Bindable
    public boolean isFinish() {
        return mIsFinish;
    }

    public void setFinish(boolean finish) {
        mIsFinish = finish;
        notifyPropertyChanged(BR.finish);
    }

    @Bindable
    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
        notifyPropertyChanged(BR.selected);
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
    public void setPresenter(BookingDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetBookingError(String msg) {
        mNavigator.showToast(msg);
    }

    @Override
    public void onGetBookingSuccess(BookingOder bookingOder) {
        setBookingOder(bookingOder);
        setChangeStatus(Status.getStatuses(bookingOder.getStatus()).size() > 0);
    }

    @Override
    public void finishRefresh() {
        setFinish(true);
    }

    @Override
    public void showProgressBar() {
        setProgressBarVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        setProgressBarVisibility(View.GONE);
    }

    @Override
    public void callCustomer(View button) {
        FloatingActionMenu fapMenu = (FloatingActionMenu) button.getParent();
        fapMenu.close(true);
        if (ActivityCompat.checkSelfPermission(mNavigator.getContext(),
            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Utils.requestCallPermission(mNavigator.getContext());
        } else {
            onPermissionGranted();
        }
    }

    @Override
    public void editBooking(View button) {
        FloatingActionMenu fapMenu = (FloatingActionMenu) button.getParent();
        fapMenu.close(true);
        mNavigator.startActivityForResult(
            EditBookingActivity.getInstance(mNavigator.getContext(), mBookingOder),
            Constant.REQUEST_ADMIN_BOOKING_ACTIVITY);
    }

    @Override
    public void messageCustomer(View button) {
        FloatingActionMenu fapMenu = (FloatingActionMenu) button.getParent();
        fapMenu.close(true);
        // TODO: 8/8/2017
    }

    @Override
    public void onPermissionGranted() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mBookingOder.getPhone()));
        mNavigator.startActivity(intent);
    }

    @Override
    public void onPermissionDenied() {
        mNavigator.showToast(R.string.msg_no_permission);
    }

    public void returnData(BookingOder order) {
        mNavigator.showToast(R.string.msg_edit_success);
        setBookingOder(order);
        setId(order.getId());
        setSelected(false);
    }

    @Override
    public void getBooking() {
        showProgressBar();
        mPresenter.getBookingById(mId);
    }

    @Override
    public void onChangeStatusClick() {
        if (mBookingOder == null) {
            return;
        }
        FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
        EditStatusDialogFragment newFragment = EditStatusDialogFragment.newInstance(mBookingOder
            .getId(), mBookingOder.getStatus());
        newFragment.show(ft, EDIT_STATUS_DIALOG);
    }

    @Bindable
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public boolean isChangeStatus() {
        return mIsChangeStatus;
    }

    public void setChangeStatus(boolean changeStatus) {
        mIsChangeStatus = changeStatus;
        notifyPropertyChanged(BR.changeStatus);
    }
}
