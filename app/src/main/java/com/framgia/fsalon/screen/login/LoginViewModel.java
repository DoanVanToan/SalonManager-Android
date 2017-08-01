package com.framgia.fsalon.screen.login;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.screen.home.HomeActivity;
import com.framgia.fsalon.screen.homeadmin.AdminHomeActivity;
import com.framgia.fsalon.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Login screen.
 */
public class LoginViewModel extends BaseObservable implements LoginContract.ViewModel {

    private LoginContract.Presenter mPresenter;
    private AppCompatActivity mActivity;
    private Context mContext;
    private int mProgressBarVisibility;
    private String mAccount;
    private String mPassWord;
    private String mAccountError;
    private String mPassWordError;
    private Navigator mNavigator;

    public LoginViewModel(AppCompatActivity activity) {
        mActivity = activity;
        mContext = mActivity.getApplicationContext();
        setProgressBarVisibility(View.GONE);
        mNavigator = new Navigator(mActivity);
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
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressbar() {
        setProgressBarVisibility(View.VISIBLE);
    }

    @Override
    public void onCustomerLoginSuccess() {
        mNavigator.startActivity(HomeActivity.getInstance(mNavigator.getContext()));
        mNavigator.finishActivity();
    }

    @Override
    public void onLoginErrror(String message) {
        Snackbar.make(mActivity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
            .show();
    }

    @Override
    public void hideProgressbar() {
        setProgressBarVisibility(View.GONE);
    }

    @Override
    public void onLoginClick() {
        mPresenter.login(mAccount, mPassWord);
    }

    @Override
    public void onRegisterClick() {
        // TODO: 20/07/2017
    }

    @Override
    public void onForgotPassWordClick() {
        // TODO: 20/07/2017
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
    public String getAccount() {
        return mAccount;
    }

    public void setAccount(String account) {
        mAccount = account;
        notifyPropertyChanged(BR.account);
    }

    @Bindable
    public String getPassWord() {
        return mPassWord;
    }

    public void setPassWord(String passWord) {
        mPassWord = passWord;
        notifyPropertyChanged(BR.passWord);
    }

    @Bindable
    public String getAccountError() {
        return mAccountError;
    }

    public void setAccountError(String accountError) {
        mAccountError = accountError;
    }

    @Bindable
    public String getPassWordError() {
        return mPassWordError;
    }

    public void setPassWordError(String passWordError) {
        mPassWordError = passWordError;
    }

    @Override
    public void onInputAccountError() {
        mAccountError = mContext.getString(R.string.msg_error_empty);
        notifyPropertyChanged(BR.accountError);
    }

    @Override
    public void onInputPassWordError() {
        mPassWordError = mContext.getString(R.string.msg_error_empty);
        notifyPropertyChanged(BR.passWordError);
    }

    @Override
    public void onAdminLoginSuccess() {
        mNavigator.startActivity(AdminHomeActivity.getInstance(mNavigator.getContext()));
        mNavigator.finishActivity();
    }
}
