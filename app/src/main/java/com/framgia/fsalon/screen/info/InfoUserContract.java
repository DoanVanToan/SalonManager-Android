package com.framgia.fsalon.screen.info;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface InfoUserContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetCurrentUserSuccess();
        void onGetCurrentUserFail();
        void onAuthenticationButtonClick();
        void showProgressbar();
        void hideProgressbar();
        void onLogoutError(String message);
        void onLogoutSuccess();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getCurrentUser();
        void logout();
        void onAuthenticationClick(boolean isLogin);
    }
}
