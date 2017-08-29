package com.framgia.fsalon.screen.editstatusdialog;

import com.framgia.fsalon.data.model.Status;
import com.framgia.fsalon.data.source.BookingRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link EditStatusDialogFragment}), retrieves the data and
 * updates the UI as required.
 */
public class EditStatusDialogPresenter implements EditStatusDialogContract.Presenter {
    private static final String TAG = EditStatusDialogPresenter.class.getName();
    private final EditStatusDialogContract.ViewModel mViewModel;
    private int mBookingId;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private BookingRepository mRepository;

    public EditStatusDialogPresenter(EditStatusDialogContract.ViewModel viewModel, int bookingId,
                                     int statusId, BookingRepository repository) {
        mViewModel = viewModel;
        mBookingId = bookingId;
        mRepository = repository;
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
        Disposable disposable = mRepository.changeStatusBooking(mBookingId, statusId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<List<String>>() {
                @Override
                public void onNext(@NonNull List<String> list) {
                    String message = "";
                    for (String msg : list) {
                        message += msg + ",";
                    }
                    mViewModel.onChangeStatusSuccess(message);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onError(e.getMessage());
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void makeStatus(int statusIdCurrent) {
        mViewModel.onGetStatusesSuccess(Status.getStatuses(statusIdCurrent));
    }
}
