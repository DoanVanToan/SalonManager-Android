package com.framgia.fsalon.screen.booking;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingRender;
import com.framgia.fsalon.data.model.BookingResponse;
import com.framgia.fsalon.data.model.DateBooking;
import com.framgia.fsalon.data.model.Salon;
import com.framgia.fsalon.data.model.Stylist;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface BookingContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void selectedSalonPosition(int position, Salon salon);

        void selectedDatePosition(int position, DateBooking dateBooking);

        void selectedTimePosition(int position, BookingRender bookingRender);

        void onError(String msg);

        void showProgressbar();

        void hideProgressbar();

        void onGetSalonsSuccess(List<Salon> salons);

        void onGetStylistSuccess(List<Stylist> stylists);

        void onGetBookingSuccess(BookingResponse bookingResponse);

        void getData();

        String getStringRes(int resId);

        void onGetDateBookingSuccess(List<DateBooking> dateBookings);

        void onBookSuccess(BookingOder bookingOder);

        void book();

        void onInputPhoneError();

        void onInputNameError();

        void onInputTimeError();

        void onInputSalonError();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getAllSalons();

        void getAllStylists(int id);

        void getBookings(int salonId, long time, int stylelistId);

        void getBookings(int salonId, long time);

        void getDateBooking();

        void book(String phone, String name, int renderBookingId, int stylistId);
    }
}
