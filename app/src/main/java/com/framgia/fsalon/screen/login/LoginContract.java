package com.framgia.fsalon.screen.login;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface   LoginContract {

    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void showProgressbar();

        void onCustomerLoginSuccess();

        void onLoginErrror(String message);

        void hideProgressbar();

        void onLoginClick();

        void onRegisterClick();

        void onForgotPassWordClick();

        void onInputAccountError();

        void onInputPassWordError();

        void onAdminLoginSuccess();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void login(String email, String passWord);

        void getCurrentUser();
    }
}
