package com.framgia.fsalon.screen.stylistbooking;

import android.support.v4.widget.DrawerLayout;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.ManageBookingResponse;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface StylistBookingContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onSelectDateClick();
        void onFilterClick(DrawerLayout layout);
        void showLoadMore();
        void onFilterSuccessfully(List<ManageBookingResponse> manageBookingResponse);
        void onFilterError();
        void hideLoadMore();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void filterBookings(int startDate, String status);
    }
}
