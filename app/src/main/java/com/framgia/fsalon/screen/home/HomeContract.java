package com.framgia.fsalon.screen.home;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface HomeContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onUserClick();
        void onBookingClick();
        void onDetailListClick();
        void onHistoryClick();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
