package com.framgia.fsalon.screen.stylistbooking;

import com.framgia.fsalon.data.model.ManageBookingResponse;
import com.framgia.fsalon.data.source.ManageBookingRepository;
import com.framgia.fsalon.utils.Utils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.framgia.fsalon.data.model.BookingOder.STATUS_CANCELED;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_FINISHED;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_IN_LATE;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_IN_PROGRESS;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_WATTING;
import static com.framgia.fsalon.data.source.remote.ManageBookingRemoteDataSource.FILTER_DAY;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_TODAY;
import static com.framgia.fsalon.utils.Constant.OUT_OF_INDEX;

/**
 * Listens to user actions from the UI ({@link StylistBookingFragment}),
 * retrieves the data and updates the UI as required.
 */
public class StylistBookingPresenter implements StylistBookingContract.Presenter {
    private static final String TAG = StylistBookingPresenter.class.getName();
    private static final String STATUS = String.valueOf(STATUS_CANCELED)
        + STATUS_WATTING
        + STATUS_FINISHED
        + STATUS_IN_LATE
        + STATUS_IN_PROGRESS;
    private final StylistBookingContract.ViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable;
    private ManageBookingRepository mRepository;

    StylistBookingPresenter(StylistBookingContract.ViewModel viewModel,
                            ManageBookingRepository repository) {
        mViewModel = viewModel;
        mRepository = repository;
        mCompositeDisposable = new CompositeDisposable();
        filterBookings(Utils.createTimeStamp(TAB_TODAY), STATUS);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void filterBookings(int startDate, String status) {
        Disposable disposable = mRepository.getListBooking(FILTER_DAY, status, startDate,
            OUT_OF_INDEX, OUT_OF_INDEX)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showLoadMore();
                }
            }).subscribeWith(new DisposableObserver<List<ManageBookingResponse>>() {
                @Override
                public void onNext(@NonNull List<ManageBookingResponse> manageBookingResponse) {
                    mViewModel.onFilterSuccessfully(manageBookingResponse);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onFilterError();
                }

                @Override
                public void onComplete() {
                    mViewModel.hideLoadMore();
                }
            });
        mCompositeDisposable.add(disposable);
    }
}
