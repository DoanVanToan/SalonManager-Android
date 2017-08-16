package com.framgia.fsalon.screen.adminbooking;

/**
 * Listens to user actions from the UI ({@link AdminBookingActivity}),
 * retrieves the data and updates
 * the UI as required.
 */
final class AdminBookingPresenter implements AdminBookingContract.Presenter {
    private static final String TAG = AdminBookingPresenter.class.getName();
    private final AdminBookingContract.ViewModel mViewModel;

    AdminBookingPresenter(AdminBookingContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
