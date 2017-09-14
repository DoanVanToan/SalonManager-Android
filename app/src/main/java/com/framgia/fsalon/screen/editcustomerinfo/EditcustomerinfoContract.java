package com.framgia.fsalon.screen.editcustomerinfo;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface EditcustomerinfoContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void showProgressbar();
        void hideProgressbar();
        void onUpdateAvatarSuccess(String urlImage);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
