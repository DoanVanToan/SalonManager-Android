package com.framgia.fsalon.screen.bill;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.BillRequest;
import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.data.model.BookingOder;
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

        void onGetBillSuccess(BillResponse billResponse);

        void onCreateBillClick();

        void onGetBookingSuccess(BookingOder bookingOder);

        void onSearchBookingClick();

        void onInputFormError();

        void onInputCustomerNameError();

        void onInputCustomerPhoneError();

        void onInputPhoneError();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getAllServices();

        void getAllStylists(int id);

        void createBill(BillRequest billRequest);

        void getBookingByPhone(String phone);

        boolean validateFormInput(Service service, Stylist stylist, String pricce, String qty);
    }
}
