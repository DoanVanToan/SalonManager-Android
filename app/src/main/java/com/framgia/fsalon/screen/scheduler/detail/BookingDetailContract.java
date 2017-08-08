package com.framgia.fsalon.screen.scheduler.detail;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.BookingOder;

/**
 * This specifies the contract between the view and the presenter.
 */
interface BookingDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetBookingError(String msg);
        void onGetBookingSuccess(BookingOder bookingOder);
        void finishRefresh();
        void showProgressBar();
        void hideProgressBar();
        void callCustomer();
        void editBooking();
        void messageCustomer();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getBookingById(int id);
    }
}
