package com.framgia.fsalon.screen.bill;

import android.text.TextUtils;

import com.framgia.fsalon.data.model.BillRequest;
import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.Salon;
import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.data.source.BillRepository;
import com.framgia.fsalon.data.source.BookingRepository;
import com.framgia.fsalon.data.source.SalonRepository;
import com.framgia.fsalon.data.source.ServiceRepository;
import com.framgia.fsalon.data.source.StylistRepository;
import com.framgia.fsalon.data.source.UserRepository;
import com.framgia.fsalon.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link BillActivity}), retrieves the data and updates
 * the UI as required.
 */
public class BillPresenter implements BillContract.Presenter {
    private static final String TAG = BillPresenter.class.getName();
    private static final int DEFAULT_SALON_ID = 1;
    private final BillContract.ViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private StylistRepository mStylistRepository;
    private ServiceRepository mServiceRepository;
    private BillRepository mBillRepository;
    private BookingRepository mBookingRepository;
    private SalonRepository mSalonRepository;
    private UserRepository mUserRepository;

    public BillPresenter(BillContract.ViewModel viewModel, int billId,
                         StylistRepository stylistRepository, ServiceRepository serviceRepository,
                         BillRepository billRepository, BookingRepository bookingRepository,
                         SalonRepository salonRepository, UserRepository userRepository) {
        mViewModel = viewModel;
        mStylistRepository = stylistRepository;
        mServiceRepository = serviceRepository;
        mBillRepository = billRepository;
        mBookingRepository = bookingRepository;
        mSalonRepository = salonRepository;
        mUserRepository = userRepository;
        getAllServices();
        getAllStylists(DEFAULT_SALON_ID);
        getAllSalon();
        getStatus();
        getBill(billId);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getAllServices() {
        Disposable disposable = mServiceRepository.getAllServices()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            }).subscribeWith(new DisposableObserver<List<Service>>() {
                @Override
                public void onNext(@NonNull List<Service> services) {
                    mViewModel.onGetServiceSuccess(services);
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
    public void createBill(BillRequest billRequest) {
        if (!validateDataInput(billRequest.getPhone(), billRequest.getCustomerName(),
            billRequest.getBillItems().size())) {
            return;
        }
        Disposable disposable = mBillRepository.createBill(billRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            }).subscribeWith(new DisposableObserver<BillResponse>() {
                @Override
                public void onNext(@NonNull BillResponse billResponse) {
                    mViewModel.onGetBillSuccess(billResponse);
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
    public void getBookingByPhone(final String phone) {
        if (!validatePhoneInput(phone)) {
            return;
        }
        Disposable disposable = mBookingRepository.getBookingByPhone(phone)
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
                    mViewModel.onGetBookingSuccess(bookingOder);
                    mViewModel.onHideCustomerPhoneError();
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.hideProgressbar();
                    getCustomerByPhone(phone);
                }

                @Override
                public void onComplete() {
                    mViewModel.hideProgressbar();
                }
            });
        mCompositeDisposable.add(disposable);
    }

    public boolean validatePhoneInput(String phone) {
        boolean isValid = true;
        if (TextUtils.isEmpty(phone)) {
            isValid = false;
            mViewModel.onInputCustomerPhoneError();
        }
        return isValid;
    }

    @Override
    public boolean validateFormInput(Service service, Stylist stylist, Salon salon, String pricce,
                                     String qty, String phoneCustomer, String nameCustomer) {
        boolean isValid = true;
        if (service == null || stylist == null || TextUtils.isEmpty(pricce) || TextUtils
            .isEmpty(qty) || salon == null || TextUtils.isEmpty(phoneCustomer) || TextUtils
            .isEmpty(nameCustomer)) {
            isValid = false;
            mViewModel.onInputFormError();
        }
        return isValid;
    }

    @Override
    public void getAllSalon() {
        Disposable disposable = mSalonRepository.getAllSalons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            }).subscribeWith(new DisposableObserver<List<Salon>>() {
                @Override
                public void onNext(@NonNull List<Salon> salons) {
                    mViewModel.onGetSalonsSuccess(salons);
                }

                @Override
                public void onError(@NonNull Throwable e) {
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
    public void getCustomerByPhone(String phone) {
        Disposable disposable = mUserRepository.getCustomerByPhone(phone)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<User>() {
                @Override
                public void onNext(@NonNull User user) {
                    mViewModel.getCustomerSuccessfull(user);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onError(e.getMessage());
                    mViewModel.hideProgressbar();
                    mViewModel.onHideCustomer();
                }

                @Override
                public void onComplete() {
                    mViewModel.hideProgressbar();
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public int setSalonPosition(BillResponse billResponse, List<Salon> salons) {
        for (Salon salon : salons) {
            if (billResponse.getDepartment().getName().equals(salon.getName())) {
                return salons.indexOf(salon);
            }
        }
        return -1;
    }

    @Override
    public void editBill(BillRequest billRequest) {
        if (!validateDataInput(billRequest.getPhone(), billRequest.getCustomerName(),
            billRequest.getBillItems().size())) {
            return;
        }
        Disposable disposable = mBillRepository.editBill(billRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            }).subscribeWith(new DisposableObserver<BillResponse>() {
                @Override
                public void onNext(@NonNull BillResponse billResponse) {
                    mViewModel.onGetBillSuccess(billResponse);
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
    public void getStatus() {
        List<String> listStatus = new ArrayList<>();
        listStatus.add(Constant.Status.STATUS_WAITING, Constant.Status.WAITING);
        listStatus.add(Constant.Status.STATUS_COMPLETE, Constant.Status.COMPLETE);
        listStatus.add(Constant.Status.STATUS_CANCEL, Constant.Status.CANCEL);
        mViewModel.onGetStatusSuccess(listStatus);
    }

    @Override
    public void getBill(int id) {
        if (id == -1) {
            return;
        }
        Disposable disposable = mBillRepository.getBillById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            }).subscribeWith(new DisposableObserver<BillResponse>() {
                @Override
                public void onNext(@NonNull BillResponse billResponse) {
                    mViewModel.onGetEditBillSuccess(billResponse);
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

    public boolean validateDataInput(String phone, String name, int totalItem) {
        boolean isValid = true;
        if (TextUtils.isEmpty(phone)) {
            isValid = false;
            mViewModel.onInputCustomerPhoneError();
        }
        if (TextUtils.isEmpty(name)) {
            isValid = false;
            mViewModel.onInputCustomerNameError();
        }
        if (totalItem <= 0) {
            isValid = false;
            mViewModel.onInputFormError();
        }
        return isValid;
    }
}
