package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.UserRespone;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by MyPC on 20/07/2017.
 */
public interface UserDataSource {
    interface LocalDataSource {
        Observable<UserRespone> getCurrentUser();
        Observable<Boolean> saveCurrentUser(UserRespone userRespone);
        void clearCurrentUser();

        Observable<Boolean> saveCurrentPhone(String phone);

        Observable<String> getCurrentPhone();
    }

    interface RemoteDataSource {
        Observable<UserRespone> login(String account, String passWord);
        Observable<List<String>> logout();
        Observable<UserRespone> registry(String email, String password, String rePassword, String
            name, String phone);
    }
}