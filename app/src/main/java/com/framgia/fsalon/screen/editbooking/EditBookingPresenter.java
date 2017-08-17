package com.framgia.fsalon.screen.editbooking;

/**
 * Listens to user actions from the UI ({@link EditBookingActivity}),
 * retrieves the data and updates
 * the UI as required.
 */
final class EditBookingPresenter implements EditBookingContract.Presenter {
    private static final String TAG = EditBookingPresenter.class.getName();
    private final EditBookingContract.ViewModel mViewModel;

    EditBookingPresenter(EditBookingContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }
}
