package com.framgia.fsalon.screen.customerinfo.user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.screen.editcustomerinfo.EditcustomerinfoActivity;
import com.framgia.fsalon.utils.Utils;
import com.framgia.fsalon.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the User screen.
 */
public class UserViewModel extends BaseObservable implements UserContract.ViewModel {
    private UserContract.Presenter mPresenter;
    private User mUser;
    private Navigator mNavigator;
    private boolean mIsEdit = false;
    private UserFragment mFragment;

    public UserViewModel(Fragment fragment, User user) {
        mNavigator = new Navigator(fragment);
        mUser = user;
        mFragment = (UserFragment) fragment;
    }

    @Bindable
    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
        notifyPropertyChanged(BR.user);
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
    public void setPresenter(UserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onCallClick() {
        if (ActivityCompat.checkSelfPermission(mNavigator.getContext(),
            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Utils.requestCallPermission(mNavigator.getContext());
        } else {
            onPermissionGranted();
        }
    }

    @Override
    public void onMessageClick() {
    }

    @Override
    public void onPermissionGranted() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mUser.getPhone()));
        mNavigator.startActivity(intent);
    }

    @Override
    public void onPermissionDenied() {
        mNavigator.showToast(R.string.msg_no_permission);
    }

    @Bindable
    public boolean isEdit() {
        return mIsEdit;
    }

    public void setEdit(boolean edit) {
        mIsEdit = edit;
        notifyPropertyChanged(BR.edit);
    }

    public void onEditButtonClick() {
        setEdit(true);
        mFragment
            .startActivityForResult(EditcustomerinfoActivity.getInstance(mNavigator.getContext(),
                mUser), 1);
    }
}
