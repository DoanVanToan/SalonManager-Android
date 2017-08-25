package com.framgia.fsalon.screen.billcustomer;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.BillResponse;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface BillCustomerContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onHideProgressBar();
        void onShowProgressBar();
        void onGetBillSuccessfully(List<BillResponse> listBillResponds);
        void onError(String message);
        void onBillDetailClick(BillResponse bill);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getBillsByCustomerId(int customerId);
    }
}
