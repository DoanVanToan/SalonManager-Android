package com.framgia.fsalon.screen.scheduler.detail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.User;

import java.io.File;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface BookingDetailContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetBookingError(String msg);
        void onGetBookingSuccess(BookingOder bookingOder);
        void finishRefresh();
        void showProgressBar();
        void hideProgressBar();
        void callCustomer(View button);
        void editBooking(View button);
        void messageCustomer(View button);
        void onPermissionGranted();
        void onPermissionDenied();
        void returnData(BookingOder order);
        void getBooking();
        void onChangeStatusClick(View view);
        void onGetData();
        void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                        @NonNull int[] grantResults);
        void onActivityResult(int requestCode, int resultCode, Intent data);
        void onAddPhoto(View view);
        void onDeterminePermissionSuccessfully(User user);
        void onUserClick();
        void onGetUserSuccess(User user);
        void onGetUserFailed(String message);
        void onUpdatePhotos();
        void onHideUpdatePhoto();
        void onShowUpdatePhoto();
        void onRequestEnoughPhotos();
        void onAddPhotoSucessfully();
        void onAddPhotoError();
        void onFramePhotoVisibility(int visibility);
        void onTxtUpdateVisibility(int visibility);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getBookingById(int id);
        void postMutiImages(@NonNull int bookingOrderId, @NonNull List<File> images,
                            String mediaType, String folder, int totalcapture);
        void determinePermission();
        void getUserByPhone(String phoneNumber);
        boolean checkValidatePhoto(List<File> photos, int total);
        void onShowPhotoCustomer(int permission);
    }
}
