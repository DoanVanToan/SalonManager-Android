package com.framgia.fsalon.screen.billdetail;

import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.data.source.BillRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link BillDetailActivity}), retrieves the data and updates
 * the UI as required.
 */
public class BillDetailPresenter implements BillDetailContract.Presenter {
    private static final String TAG = BillDetailPresenter.class.getName();
    private final BillDetailContract.ViewModel mViewModel;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private BillRepository mBillRepository;

    public BillDetailPresenter(BillDetailContract.ViewModel viewModel, int billId, BillRepository
        billRepository) {
        mViewModel = viewModel;
        mBillRepository = billRepository;
        getBillById(billId);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getBillById(int id) {
        Disposable disposable = mBillRepository.getBillById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            })
            .subscribeWith(new DisposableObserver<BillResponse>() {
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
}
