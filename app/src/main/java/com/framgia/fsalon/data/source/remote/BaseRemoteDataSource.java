package com.framgia.fsalon.data.source.remote;

import com.framgia.fsalon.data.source.api.FSalonApi;

/**
 * Created by MyPC on 20/07/2017.
 */
public abstract class BaseRemoteDataSource {
    protected FSalonApi mFSalonApi;

    public BaseRemoteDataSource(FSalonApi FSalonApi) {
        mFSalonApi = FSalonApi;
    }
}
