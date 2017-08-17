package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.model.CustomerResponse;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.data.model.UserRespone;
import com.framgia.fsalon.data.source.UserDataSource;
import com.framgia.fsalon.data.source.api.FSalonApi;
import com.framgia.fsalon.utils.Utils;

import java.util.List;

import framgia.retrofitservicecreator.api.model.Respone;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by MyPC on 20/07/2017.
 */
public class UserRemoteDataSource extends BaseRemoteDataSource
    implements UserDataSource.RemoteDataSource {
    public UserRemoteDataSource(FSalonApi FSalonApi) {
        super(FSalonApi);
    }

    @Override
    public Observable<UserRespone> login(String account, String passWord) {
        return mFSalonApi.login(account, passWord).flatMap(
            new Function<Respone<UserRespone>, ObservableSource<UserRespone>>() {
                @Override
                public ObservableSource<UserRespone> apply(
                    @NonNull Respone<UserRespone> userResponeRespone)
                    throws Exception {
                    return Utils.getResponse(userResponeRespone);
                }
            });
    }

    @Override
    public Observable<List<String>> logout() {
        return mFSalonApi.logout()
            .flatMap(new Function<Respone<List<String>>, ObservableSource<List<String>>>() {
                @Override
                public ObservableSource<List<String>> apply(@NonNull Respone<List<String>>
                                                                listRespone)
                    throws Exception {
                    return Utils.getResponse(listRespone);
                }
            });
    }

    @Override
    public Observable<UserRespone> registry(String email, String password, String rePassword,
                                            String name, String phone) {
        return mFSalonApi.registry(email, password, rePassword, name, phone).flatMap(
            new Function<Respone<UserRespone>, ObservableSource<UserRespone>>() {
                @Override
                public ObservableSource<UserRespone> apply
                    (@NonNull Respone<UserRespone> userResponeRespone)
                    throws Exception {
                    return Utils.getResponse(userResponeRespone);
                }
            });
    }

    @Override
    public Observable<User> getCustomerByPhone(String phone) {
        return mFSalonApi.getCustomerByPhone(phone)
            .flatMap(new Function<Respone<User>, ObservableSource<User>>() {
                @Override
                public ObservableSource<User> apply(@NonNull Respone<User> listRespone)
                    throws Exception {
                    return Utils.getResponse(listRespone);
                }
            });
    }

    @Override
    public Observable<CustomerResponse> getAllCustomers(int page, int perpage) {
        return mFSalonApi.getAllCustomers(page, perpage).flatMap(
            new Function<Respone<CustomerResponse>,
                ObservableSource<CustomerResponse>>() {
                @Override
                public ObservableSource<CustomerResponse> apply(
                    @NonNull Respone<CustomerResponse> bookingCustomerResponseRespone)
                    throws Exception {
                    return Utils.getResponse(bookingCustomerResponseRespone);
                }
            });
    }
}
