package com.framgia.fsalon.screen.listbill;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ListBillContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onCreateBillClick();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
