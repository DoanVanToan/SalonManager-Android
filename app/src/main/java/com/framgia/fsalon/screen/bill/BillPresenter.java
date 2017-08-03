package com.framgia.fsalon.screen.bill;

import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.data.source.ServiceRepository;
import com.framgia.fsalon.data.source.StylistRepository;

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
    private final BillContract.ViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private StylistRepository mStylistRepository;
    private ServiceRepository mServiceRepository;

    public BillPresenter(BillContract.ViewModel viewModel, StylistRepository stylistRepository,
                         ServiceRepository serviceRepository) {
        mViewModel = viewModel;
        mStylistRepository = stylistRepository;
        mServiceRepository = serviceRepository;
        getAllServices();
        getAllStylists(1);
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
}
