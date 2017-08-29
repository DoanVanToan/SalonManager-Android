package com.framgia.fsalon.screen.editstatusdialog;

import com.framgia.fsalon.data.model.Status;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Listens to user actions from the UI ({@link EditStatusDialogFragment}), retrieves the data and
 * updates the UI as required.
 */
public class EditStatusDialogPresenter implements EditStatusDialogContract.Presenter {
    private static final String TAG = EditStatusDialogPresenter.class.getName();
    private final EditStatusDialogContract.ViewModel mViewModel;
    private int mBookingId;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public EditStatusDialogPresenter(EditStatusDialogContract.ViewModel viewModel, int bookingId,
                                     int statusId) {
        mViewModel = viewModel;
        mBookingId = bookingId;
        makeStatus(statusId);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void changeStatusBooking(int statusId) {
        // TODO: 29/08/2017
    }

    @Override
    public void makeStatus(int statusIdCurrent) {
        mViewModel.onGetStatusesSuccess(Status.getStatuses(statusIdCurrent));
    }
}
