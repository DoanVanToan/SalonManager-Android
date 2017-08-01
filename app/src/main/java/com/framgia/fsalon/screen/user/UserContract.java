package com.framgia.fsalon.screen.user;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface UserContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onCallClick();
        void onMessageClick();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
