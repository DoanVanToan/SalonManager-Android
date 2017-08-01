package com.framgia.fsalon.screen.detail;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface DetailContract {
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
