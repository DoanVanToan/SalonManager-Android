package com.framgia.fsalon.screen.imagedetail;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ImageDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onPreviouslyClick();
        void onNextClick();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
