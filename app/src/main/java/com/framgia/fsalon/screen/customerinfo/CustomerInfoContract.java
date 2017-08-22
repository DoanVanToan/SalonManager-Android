package com.framgia.fsalon.screen.customerinfo;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface CustomerInfoContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
