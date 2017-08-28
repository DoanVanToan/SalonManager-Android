package com.framgia.fsalon.screen.imagecustomer;

import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.data.source.BillRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link ImageCustomerFragment}), retrieves the data and
 * updates the UI as required.
 */
public class ImageCustomerPresenter implements ImageCustomerContract.Presenter {
    private static final String TAG = ImageCustomerPresenter.class.getName();
    private final ImageCustomerContract.ViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private BillRepository mBillRepository;

    public ImageCustomerPresenter(ImageCustomerContract.ViewModel viewModel,
                                  BillRepository billRepository, int customerId) {
        mViewModel = viewModel;
        mBillRepository = billRepository;
        getBillsByCustomerId(customerId);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getBillsByCustomerId(int customerId) {
        Disposable disposable = mBillRepository.getBillsByCustomerId(customerId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.onShowProgressBar();
                }
            }).subscribeWith(new DisposableObserver<List<BillResponse>>() {
                @Override
                public void onNext(@NonNull List<BillResponse> billResponses) {
                    mViewModel.onGetBillSuccessfully(billResponses);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onHideProgressBar();
                    mViewModel.onError(e.getMessage());
                }

                @Override
                public void onComplete() {
                    mViewModel.onHideProgressBar();
                }
            });
        mCompositeDisposable.add(disposable);
    }
}
