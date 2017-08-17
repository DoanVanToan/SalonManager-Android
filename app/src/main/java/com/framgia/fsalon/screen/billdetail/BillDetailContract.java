package com.framgia.fsalon.screen.billdetail;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.BillResponse;

/**
 * This specifies the contract between the view and the presenter.
 */
interface BillDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void showProgressbar();
        void hideProgressbar();
        void onError(String message);
        void onGetBillSuccess(BillResponse billResponse);
        void onEditBillClick();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getBillById(int id);
    }
}
