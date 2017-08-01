package com.framgia.fsalon.screen.booking;

import android.text.TextUtils;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.BookingResponse;
import com.framgia.fsalon.data.model.DateBooking;
import com.framgia.fsalon.data.model.Salon;
import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.data.source.BookingRepository;
import com.framgia.fsalon.data.source.SalonRepository;
import com.framgia.fsalon.data.source.StylistRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.framgia.fsalon.utils.Constant.A_DAY;

/**
 * Listens to user actions from the UI ({@link BookingFragment}), retrieves the data and updates
 * the UI as required.
 */
public class BookingPresenter implements BookingContract.Presenter {
    private static final String TAG = BookingPresenter.class.getName();
    private final BookingContract.ViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private BookingRepository mBookingRepository;
    private SalonRepository mSalonRepository;
    private StylistRepository mStylistRepository;

    public BookingPresenter(BookingContract.ViewModel viewModel,
                            BookingRepository bookingRepository, SalonRepository salonRepository,
                            StylistRepository stylistRepository) {
        mViewModel = viewModel;
        mBookingRepository = bookingRepository;
        mSalonRepository = salonRepository;
        mStylistRepository = stylistRepository;
        getAllSalons();
        getDateBooking();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getAllSalons() {
        Disposable disposable = mSalonRepository.getAllSalons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            })
            .subscribeWith(
                new DisposableObserver<List<Salon>>() {
                    @Override
                    public void onNext(@NonNull List<Salon> salons) {
                        mViewModel.onGetSalonsSuccess(salons);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mViewModel.hideProgressbar();
                        mViewModel.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mViewModel.hideProgressbar();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getAllStylists(int id) {
        Disposable disposable = mStylistRepository.getAllStylists(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            }).subscribeWith(new DisposableObserver<List<Stylist>>() {
                @Override
                public void onNext(@NonNull List<Stylist> stylists) {
                    mViewModel.onGetStylistSuccess(stylists);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.hideProgressbar();
                    mViewModel.onError(e.getMessage());
                }

                @Override
                public void onComplete() {
                    mViewModel.hideProgressbar();
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getBookings(int salonId, long time, int stylelistId) {
        Disposable disposable = mBookingRepository.getBookings(salonId, time, stylelistId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            }).subscribeWith(new DisposableObserver<BookingResponse>() {
                @Override
                public void onNext(@NonNull BookingResponse bookingResponse) {
                    mViewModel.onGetBookingSuccess(bookingResponse);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.hideProgressbar();
                    mViewModel.onError(e.getMessage());
                }

                @Override
                public void onComplete() {
                    mViewModel.hideProgressbar();
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getBookings(int salonId, long time) {
        Disposable disposable = mBookingRepository.getBookings(salonId, time)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            }).subscribeWith(new DisposableObserver<BookingResponse>() {
                @Override
                public void onNext(@NonNull BookingResponse bookingResponse) {
                    mViewModel.onGetBookingSuccess(bookingResponse);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.hideProgressbar();
                    mViewModel.onError(e.getMessage());
                }

                @Override
                public void onComplete() {
                    mViewModel.hideProgressbar();
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getDateBooking() {
        List<DateBooking> dateBookings = new ArrayList<>();
        dateBookings.add(new DateBooking(mViewModel.getStringRes(R.string.title_today),
            System.currentTimeMillis()));
        dateBookings.add(
            new DateBooking(mViewModel.getStringRes(R.string.title_tomorrow),
                System.currentTimeMillis()
                    + A_DAY));
        dateBookings
            .add(new DateBooking(mViewModel.getStringRes(R.string.title_after_tomorrow),
                System.currentTimeMillis() + A_DAY * 2));
        mViewModel.onGetDateBookingSuccess(dateBookings);
    }

    @Override
    public void book(String phone, String name, int renderBookingId, int stylistId) {
        if (!validateDataInput(phone, name, renderBookingId)) {
            return;
        }
        Disposable disposable = mBookingRepository.book(phone, name, renderBookingId, stylistId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            }).subscribeWith(new DisposableObserver<BookingOder>() {
                @Override
                public void onNext(@NonNull BookingOder bookingOder) {
                    mViewModel.onBookSuccess(bookingOder);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.hideProgressbar();
                    mViewModel.onError(e.getMessage());
                }

                @Override
                public void onComplete() {
                    mViewModel.hideProgressbar();
                }
            });
        mCompositeDisposable.add(disposable);
    }

    public boolean validateDataInput(String phone, String name, int renderBookingId) {
        boolean isValid = true;
        if (TextUtils.isEmpty(phone)) {
            isValid = false;
            mViewModel.onInputPhoneError();
        }
        if (TextUtils.isEmpty(name)) {
            isValid = false;
            mViewModel.onInputNameError();
        }
        if (renderBookingId <= 0) {
            isValid = false;
            mViewModel.onInputTimeError();
        }
        return isValid;
    }
}
