package com.framgia.fsalon.screen.login;

import android.text.TextUtils;
import android.util.Log;
import com.framgia.fsalon.data.model.UserRespone;
import com.framgia.fsalon.data.source.UserRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.framgia.fsalon.utils.Constant.Permission.PERMISSION_ADMIN;

/**
 * Listens to user actions from the UI ({@link LoginActivity}), retrieves the data and updates
 * the UI as required.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private static final String TAG = LoginPresenter.class.getName();
    private final LoginContract.ViewModel mViewModel;
    private UserRepository mRepository;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public LoginPresenter(LoginContract.ViewModel viewModel, UserRepository repository) {
        mViewModel = viewModel;
        mRepository = repository;
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
    public void login(String account, String passWord) {
        if (!validateDataInput(account, passWord)) {
            return;
        }
        Disposable disposable = mRepository.login(account, passWord)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(new Consumer<Disposable>() {
                @Override
                public void accept(@NonNull Disposable disposable) throws Exception {
                    mViewModel.showProgressbar();
                }
            }).subscribeWith(new DisposableObserver<UserRespone>() {
                @Override
                public void onNext(@NonNull UserRespone userRespone) {
                    loginWithPermission(userRespone.getUser().getPermission());
                    mRepository.saveCurrentUser(userRespone).subscribe();
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    mViewModel.hideProgressbar();
                    mViewModel.onLoginErrror(e.getMessage());
                }

                @Override
                public void onComplete() {
                    mViewModel.hideProgressbar();
                }
            });
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void getCurrentUser() {
        Disposable disposable = mRepository.getCurrentUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<UserRespone>() {
                @Override
                public void onNext(@NonNull UserRespone userRespone) {
                    if (userRespone.getUser() != null) {
                        loginWithPermission(userRespone.getUser().getPermission());
                    }
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.d(TAG, "call: " + e.getMessage());
                }

                @Override
                public void onComplete() {
                }
            });
        mCompositeDisposable.add(disposable);
    }

    public boolean validateDataInput(String account, String password) {
        boolean isValid = true;
        if (TextUtils.isEmpty(account)) {
            isValid = false;
            mViewModel.onInputAccountError();
        }
        if (TextUtils.isEmpty(password)) {
            isValid = false;
            mViewModel.onInputPassWordError();
        }
        return isValid;
    }

    private void loginWithPermission(int permission) {
        if (permission == PERMISSION_ADMIN) {
            mViewModel.onAdminLoginSuccess();
        } else {
            mViewModel.onCustomerLoginSuccess();
        }
    }
}
