package com.framgia.fsalon.screen.info;

import com.framgia.fsalon.data.model.UserRespone;
import com.framgia.fsalon.data.source.UserRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Listens to user actions from the UI ({@link InfoUserFragment}), retrieves the data and updates
 * the UI as required.
 */
public class InfoUserPresenter implements InfoUserContract.Presenter {
    private final InfoUserContract.ViewModel mViewModel;
    private UserRepository mRepository;
    private CompositeDisposable mCompositeDisposable;

    public InfoUserPresenter(InfoUserContract.ViewModel viewModel,
                             UserRepository userRepository) {
        mViewModel = viewModel;
        mRepository = userRepository;
        mCompositeDisposable = new CompositeDisposable();
        getCurrentUser();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    @Override
    public void getCurrentUser() {
        Disposable disposable = mRepository.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<UserRespone>() {
                @Override
                public void onNext(@NonNull UserRespone userRespone) {
                    mViewModel.onGetCurrentUserSuccess();
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.onGetCurrentUserFail();
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void logout() {
        Disposable disposable = mRepository.logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            }).subscribeWith(new DisposableObserver<List<String>>() {
                @Override
                public void onNext(@NonNull List<String> strings) {
                    mRepository.clearCurrentUser();
                    mViewModel.onLogoutSuccess();
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.hideProgressbar();
                    mViewModel.onLogoutError(e.getMessage());
                }

                @Override
                public void onComplete() {
                    mViewModel.hideProgressbar();
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onAuthenticationClick(boolean isLogin) {
        if (isLogin) {
            logout();
        } else {
            mViewModel.onLogoutSuccess();
        }
    }
}
