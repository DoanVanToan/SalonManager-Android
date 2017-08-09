package com.framgia.fsalon.screen.info;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.screen.login.LoginActivity;
import com.framgia.fsalon.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the InfoUser screen.
 */
public class InfoUserViewModel extends BaseObservable implements InfoUserContract.ViewModel {
    private InfoUserContract.Presenter mPresenter;
    private int mProgressBarVisibility;
    private boolean mIsLogin;
    private Navigator mNavigator;

    public InfoUserViewModel(Fragment fragment) {
        mNavigator = new Navigator(fragment);
        setProgressBarVisibility(View.GONE);
    }

    @Bindable
    public boolean isLogin() {
        return mIsLogin;
    }

    public void setLogin(boolean isLogin) {
        mIsLogin = isLogin;
        notifyPropertyChanged(BR.login);
    }

    @Bindable
    public int getProgressBarVisibility() {
        return mProgressBarVisibility;
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
    public void setPresenter(InfoUserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setProgressBarVisibility(int progressBarVisibility) {
        mProgressBarVisibility = progressBarVisibility;
        notifyPropertyChanged(BR.progressBarVisibility);
    }

    public void onGetCurrentUserSuccess() {
        setLogin(true);
    }

    @Override
    public void onGetCurrentUserFail() {
        setLogin(false);
    }

    @Override
    public void onAuthenticationButtonClick() {
        mPresenter.onAuthenticationClick(mIsLogin);
    }

    @Override
    public void showProgressbar() {
        setProgressBarVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        setProgressBarVisibility(View.GONE);
    }

    @Override
    public void onLogoutError(String message) {
        mNavigator.showToast(message);
    }

    @Override
    public void onLogoutSuccess() {
        mNavigator.startActivity(LoginActivity.getInstance(mNavigator.getContext()));
        mNavigator.finishActivity();
    }
}
