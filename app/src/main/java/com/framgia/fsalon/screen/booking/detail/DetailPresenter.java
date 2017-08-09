package com.framgia.fsalon.screen.booking.detail;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.UserRespone;
import com.framgia.fsalon.data.source.BookingRepository;
import com.framgia.fsalon.data.source.UserRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link DetailFragment}), retrieves the data and updates
 * the UI as required.
 */
public class DetailPresenter implements DetailContract.Presenter {
    private static final String TAG = DetailPresenter.class.getName();
    private final DetailContract.ViewModel mViewModel;
    private UserRepository mUserRepository;
    private BookingRepository mBookingRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private String mPhone;

    DetailPresenter(DetailContract.ViewModel viewModel, UserRepository userRepository,
                    BookingRepository bookingRepository) {
        mViewModel = viewModel;
        mUserRepository = userRepository;
        mBookingRepository = bookingRepository;
    }

    @Override
    public void onStart() {
        Disposable disposable = mUserRepository.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<UserRespone>() {
                @Override
                public void onNext(@NonNull UserRespone userRespone) {
                    mPhone = userRespone.getUser().getPhone();
                    getCurrentPhone();
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    getCurrentPhone();
                    mViewModel.finishRefresh();
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
        getCurrentPhone();
    }

    @Override
    public void getBookingByPhone() {
        if (mPhone.isEmpty()) {
            mViewModel.onNotBooking();
        } else {
            Disposable disposable = mBookingRepository.getBookingByPhone(mPhone)
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
                        mViewModel.finishRefresh();
                    }

                    @Override
                    public void onComplete() {
                        mViewModel.finishRefresh();
                    }
                });
            mCompositeDisposable.add(disposable);
        }
    }

    @Override
    public void getCurrentPhone() {
        Disposable disposable = mUserRepository.getCurrentPhone()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<String>() {
                @Override
                public void onNext(@NonNull String s) {
                    mPhone = s;
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    if (mPhone.isEmpty()) {
                        mViewModel.onNotBooking();
                        mViewModel.finishRefresh();
                    } else {
                        getBookingByPhone();
                    }
                }

                @Override
                public void onComplete() {
                    getBookingByPhone();
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }
}
