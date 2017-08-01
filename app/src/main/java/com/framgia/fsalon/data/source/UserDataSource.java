package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.UserRespone;

import io.reactivex.Observable;

/**
 * Created by MyPC on 20/07/2017.
 */
public interface UserDataSource {

    interface LocalDataSource {

        Observable<UserRespone> getCurrentUser();

        Observable<Boolean> saveCurrentUser(UserRespone userRespone);
    }

    interface RemoteDataSource {

        Observable<UserRespone> login(String account, String passWord);

        Observable<UserRespone> registry(String email, String password, String rePassword, String
            name, String phone);
    }
}