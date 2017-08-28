package com.framgia.fsalon.screen.imagecustomer;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.BillResponse;

import java.util.List;

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
        void onGetBillSuccessfully(List<BillResponse> billResponses);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getBillsByCustomerId(int customerId);
    }
}
