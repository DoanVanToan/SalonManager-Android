package com.framgia.fsalon.screen.imagecustomer;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ImageCustomerContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onHideProgressBar();
        void onShowProgressBar();
        void onError(String message);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
