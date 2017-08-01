package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.source.UserDataSource;
import com.framgia.fsalon.data.model.UserRespone;
import com.framgia.fsalon.data.source.api.FSalonApi;
import com.framgia.fsalon.utils.Utils;

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
}
