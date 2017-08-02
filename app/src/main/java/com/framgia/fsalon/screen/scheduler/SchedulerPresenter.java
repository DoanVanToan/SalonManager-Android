package com.framgia.fsalon.screen.scheduler;

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

import static com.framgia.fsalon.data.source.remote.ManageBookingRemoteDataSource.FILTER_DAY;
import static com.framgia.fsalon.utils.Constant.ApiParram.FIRST_PAGE;
import static com.framgia.fsalon.utils.Constant.OUT_OF_INDEX;

/**
 * Listens to user actions from the UI ({@link SchedulerFragment}), retrieves the data and updates
 * the UI as required.
 */
public class SchedulerPresenter implements SchedulerContract.Presenter {
    private final SchedulerContract.ViewModel mViewModel;
    private ManageBookingRepository mRepository;
    private int mPage = FIRST_PAGE;
    private CompositeDisposable mCompositeDisposable;

    SchedulerPresenter(SchedulerContract.ViewModel viewModel, ManageBookingRepository
        repository) {
        mViewModel = viewModel;
        mRepository = repository;
        mCompositeDisposable = new CompositeDisposable();
        getSchedulers(FILTER_DAY, mPage, OUT_OF_INDEX, OUT_OF_INDEX,
            Utils.createTimeStamp(SchedulerViewModel.TabFilter.TAB_TODAY), OUT_OF_INDEX);
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getSchedulers(String filterChoice, int page, int perpage, int status, int startDate,
                              int endDate) {
        Disposable disposable = mRepository.getListBooking(filterChoice, page, perpage, status,
            startDate, endDate)
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
                    mViewModel.onSchedulerSuccessful(manageBookingResponse);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onSchedulerFail();
                    mViewModel.hideLoadMore();
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
        mPage = page;
    }

    @Override
    public void loadMoreData() {
        mPage++;
        getSchedulers(FILTER_DAY, mPage, OUT_OF_INDEX, OUT_OF_INDEX, OUT_OF_INDEX, OUT_OF_INDEX);
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }
}
