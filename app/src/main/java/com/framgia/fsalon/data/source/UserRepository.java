package com.framgia.fsalon.data.source;

import com.framgia.fsalon.data.model.CustomerResponse;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.data.model.UserRespone;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by MyPC on 20/07/2017.
 */
public class UserRepository
    implements UserDataSource.LocalDataSource, UserDataSource.RemoteDataSource {
    private UserDataSource.RemoteDataSource mUserRemoteDataSource;
    private UserDataSource.LocalDataSource mUserLocalDataSource;

    public UserRepository(UserDataSource.RemoteDataSource userRemoteDataSource,
                          UserDataSource.LocalDataSource userLocalDataSource) {
        mUserRemoteDataSource = userRemoteDataSource;
        mUserLocalDataSource = userLocalDataSource;
    }

    @Override
    public Observable<UserRespone> login(String account, String passWord) {
        return mUserRemoteDataSource.login(account, passWord);
    }

    @Override
    public Observable<List<String>> logout() {
        return mUserRemoteDataSource.logout();
    }

    @Override
    public Observable<UserRespone> getCurrentUser() {
        return mUserLocalDataSource.getCurrentUser();
    }

    @Override
    public Observable<Boolean> saveCurrentUser(UserRespone userRespone) {
        return mUserLocalDataSource.saveCurrentUser(userRespone);
    }

    @Override
    public void clearCurrentUser() {
        mUserLocalDataSource.clearCurrentUser();
    }

    @Override
    public Observable<UserRespone> registry(String email, String password, String rePassword,
                                            String name, String phone) {
        return mUserRemoteDataSource.registry(email, password, rePassword, name, phone);
    }

    public Observable<String> getCurrentPhone() {
        return mUserLocalDataSource.getCurrentPhone();
    }

    public Observable<Boolean> saveCurrentPhone(String phone) {
        return mUserLocalDataSource.saveCurrentPhone(phone);
    }

    @Override
    public Observable<User> getCustomerByPhone(String phone) {
        return mUserRemoteDataSource.getCustomerByPhone(phone);
    }

    @Override
    public Observable<CustomerResponse> getAllCustomers(int page, int perPage) {
        return mUserRemoteDataSource.getAllCustomers(page, perPage);
    }
}
