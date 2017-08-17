package com.framgia.fsalon.screen.user;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.source.BookingRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link UserActivity}), retrieves the data and updates
 * the UI as required.
 */
final class UserPresenter implements UserContract.Presenter {
    private static final String TAG = UserPresenter.class.getName();
    private final UserContract.ViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private BookingRepository mBookingRepository;

    UserPresenter(UserContract.ViewModel viewModel, String phone,
                         BookingRepository bookingRepository) {
        mViewModel = viewModel;
        mBookingRepository = bookingRepository;
        getCustomerBooking(phone);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getCustomerBooking(String phone) {
        Disposable disposable = mBookingRepository.getBookingByPhone(phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<BookingOder>() {
                @Override
                public void onNext(@NonNull BookingOder bookingOder) {
                    mViewModel.onGetBookingSuccess(bookingOder);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onGetBookingError(e.getMessage());
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }
}
