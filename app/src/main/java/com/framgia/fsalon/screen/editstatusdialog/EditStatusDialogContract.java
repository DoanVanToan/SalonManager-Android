package com.framgia.fsalon.screen.editstatusdialog;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.Status;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface EditStatusDialogContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onChangeStatusClick();
        void onCancelClick();
        void onGetStatusesSuccess(List<Status> statuses);
        void onChangeStatusSuccess(String s);
        void onError(String message);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void changeStatusBooking(int statusId);
        void makeStatus(int statusIdCurrent);
    }
}
