package com.framgia.fsalon.screen.scheduler.detail;

import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.ImageResponse;
import com.framgia.fsalon.data.model.User;
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
 * Listens to user actions from the UI ({@link BookingDetailActivity}),
 * retrieves the data and updates
 * the UI as required.
 */
final class BookingDetailPresenter implements BookingDetailContract.Presenter {

    private static final String TAG = BookingDetailPresenter.class.getName();
    private final BookingDetailContract.ViewModel mViewModel;
    private BookingRepository mBookingRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private UserRepository mUserRepository;

    BookingDetailPresenter(BookingDetailContract.ViewModel viewModel,
        BookingRepository bookingRepository, UserRepository userRepository) {
        mViewModel = viewModel;
        mBookingRepository = bookingRepository;
        mUserRepository = userRepository;
        determinePermission();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getBookingById(int id) {
        Disposable disposable = mBookingRepository.getBookingById(id)
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
                    mViewModel.hideProgressBar();
                }

                @Override
                public void onComplete() {
                    mViewModel.finishRefresh();
                    mViewModel.hideProgressBar();
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void postImageByStylist(@NonNull int bookingOrderId, @NonNull ImageResponse image) {
        Disposable disposable = mBookingRepository.postImageByStylist(bookingOrderId, image)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<BookingOder>() {
                @Override
                public void onNext(@NonNull BookingOder bookingOder) {
                }

                @Override
                public void onError(@NonNull Throwable e) {
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void determinePermission() {
        Disposable disposable = mUserRepository.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<UserRespone>() {
                @Override
                public void onNext(@NonNull UserRespone userRespone) {
                    if (userRespone.getUser() != null) {
                        mViewModel.onDeterminePermissionSuccessfully(userRespone.getUser());
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getUserByPhone(String phoneNumber) {
        mViewModel.showProgressBar();
        Disposable disposable = mUserRepository.getCustomerByPhone(phoneNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<User>() {
                @Override
                public void onNext(@NonNull User user) {
                    mViewModel.onGetUserSuccess(user);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onGetUserFailed(e.getMessage());
                    mViewModel.hideProgressBar();
                }

                @Override
                public void onComplete() {
                    mViewModel.hideProgressBar();
                }
            });
        mCompositeDisposable.add(disposable);
    }
}
