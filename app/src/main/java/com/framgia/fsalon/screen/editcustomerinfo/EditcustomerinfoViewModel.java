package com.framgia.fsalon.screen.editcustomerinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.utils.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

/**
 * Exposes the data to be used in the Editcustomerinfo screen.
 */
public class EditcustomerinfoViewModel extends BaseObservable implements EditcustomerinfoContract
    .ViewModel, DatePickerDialog.OnDateSetListener,
    DialogInterface.OnCancelListener {
    private EditcustomerinfoContract.Presenter mPresenter;
    private static final int IMAGE_PICKER_SELECT = 1;
    private static final int RESULT = 2;
    private static final String USER = "USER";
    private User mUser = new User();
    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendar;
    private EditcustomerinfoActivity mActivity;
    private boolean mProgressBarVisibility = false;

    public EditcustomerinfoViewModel(User user, EditcustomerinfoActivity activity) {
        mActivity = activity;
        mUser.setAvatar(user.getAvatar());
        mUser.setBirthday(user.getBirthday());
        mUser.setPhone(user.getPhone());
        mUser.setName(user.getName());
        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }
        mDatePickerDialog =
            DatePickerDialog.newInstance(this,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.setOnDateSetListener(this);
        mDatePickerDialog.setOnCancelListener(this);
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
    public void setPresenter(EditcustomerinfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
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
        mUser.setBirthday(Utils.convertDate(mCalendar.getTime()));
    }

    @Bindable
    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
        notifyPropertyChanged(BR.user);
    }

    public void onUpdateAvatarButtonClick() {
        Intent i = new Intent(
            Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mActivity.startActivityForResult(i, IMAGE_PICKER_SELECT);
    }

    @Override
    public void showProgressbar() {
        setProgressBarVisibility(true);
    }

    @Override
    public void hideProgressbar() {
        setProgressBarVisibility(false);
    }

    @Override
    public void onUpdateAvatarSuccess(String urlImage) {
        mUser.setAvatar(urlImage);
    }

    public void onSelectDateClick() {
        mDatePickerDialog.show(mActivity.getFragmentManager(), "");
    }

    public void onCancelClick() {
        mActivity.finish();
    }

    public void onUpdateClick() {
        Intent intent = new Intent();
        Bundle args = new Bundle();
        args.putParcelable(USER, mUser);
        intent.putExtras(args);
        mActivity.setResult(RESULT, intent);
        mActivity.finish();
    }

    @Bindable
    public boolean isProgressBarVisibility() {
        return mProgressBarVisibility;
    }

    public void setProgressBarVisibility(boolean progressBarVisibility) {
        mProgressBarVisibility = progressBarVisibility;
        notifyPropertyChanged(BR.progressBarVisibility);
    }
}
