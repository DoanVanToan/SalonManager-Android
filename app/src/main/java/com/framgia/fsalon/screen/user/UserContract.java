package com.framgia.fsalon.screen.user;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.BookingOder;

/**
 * This specifies the contract between the view and the presenter.
 */
interface UserContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onCallClick();
        void onMessageClick();
        void onPermissionDenied();
        void onPermissionGranted();
        void onGetBookingSuccess(BookingOder oder);
        void onGetBookingError(String msg);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getCustomerBooking(String phone);
    }
}
