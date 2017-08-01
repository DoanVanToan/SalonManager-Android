package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.UserRespone;

import io.reactivex.Observable;

/**
 * Created by MyPC on 20/07/2017.
 */
public class UserRepository {

    private UserDataSource.RemoteDataSource mUserRemoteDataSource;
    private UserDataSource.LocalDataSource mUserLocalDataSource;

    public UserRepository(UserDataSource.RemoteDataSource userRemoteDataSource,
        UserDataSource.LocalDataSource userLocalDataSource) {
        mUserRemoteDataSource = userRemoteDataSource;
        mUserLocalDataSource = userLocalDataSource;
    }

    public Observable<UserRespone> login(String account, String passWord) {
        return mUserRemoteDataSource.login(account, passWord);
    }

    public Observable<UserRespone> getCurrentUser() {
        return mUserLocalDataSource.getCurrentUser();
    }

    public Observable<Boolean> saveCurrentUser(UserRespone userRespone) {
        return mUserLocalDataSource.saveCurrentUser(userRespone);
    }

    public Observable<UserRespone> registry(String email, String password, String rePassword,
                                            String name, String phone) {
        return mUserRemoteDataSource.registry(email, password, rePassword, name, phone);
    }
}
