package com.framgia.fsalon.screen.scheduler;

import com.framgia.fsalon.data.model.ManageBookingResponse;
import com.framgia.fsalon.data.model.Salon;
import com.framgia.fsalon.data.source.ManageBookingRepository;
import com.framgia.fsalon.data.source.SalonRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.framgia.fsalon.utils.Constant.ApiParram.FIRST_PAGE;

/**
 * Listens to user actions from the UI ({@link SchedulerFragment}), retrieves the data and updates
 * the UI as required.
 */
public class SchedulerPresenter implements SchedulerContract.Presenter {
    private final SchedulerContract.ViewModel mViewModel;
    private ManageBookingRepository mRepository;
    private int mPage = FIRST_PAGE;
    private int mPerPage = 20;
    private CompositeDisposable mCompositeDisposable;
    private SalonRepository mSalonRepository;

    SchedulerPresenter(SchedulerContract.ViewModel viewModel, ManageBookingRepository
        repository, SalonRepository salonRepository) {
        mViewModel = viewModel;
        mRepository = repository;
        mSalonRepository = salonRepository;
        mCompositeDisposable = new CompositeDisposable();
        getAllSalons();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getSchedulers(String filterChoice,
                              String status, int startDate,
                              int endDate, int departmentId) {
        Disposable disposable = mRepository.getListBooking(filterChoice, status, startDate, endDate,
            departmentId)
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
                    mViewModel.hideLoadMore();
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getAllSalons() {
        Disposable disposable = mSalonRepository.getAllSalons()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showLoadMore();
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
                        mViewModel.hideLoadMore();
                        mViewModel.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mViewModel.hideLoadMore();
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadMoreData() {
        mPage++;
        // TODO: 09/08/2017 load more get data
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }
}
