package com.framgia.fsalon.screen.bill;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.Stylist;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface BillContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onAddBillClick();

        void showProgressbar();

        void onGetStylistSuccess(List<Stylist> stylists);

        void hideProgressbar();

        void onError(String message);

        void onGetServiceSuccess(List<Service> services);

        void onDeleteItemClick(int position);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getAllServices();

        void getAllStylists(int id);
    }
}
