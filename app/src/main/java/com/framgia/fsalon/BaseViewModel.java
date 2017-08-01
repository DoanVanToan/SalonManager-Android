package com.framgia.fsalon;

/**
 * Created by MyPC on 19/07/2017.
 */
public interface BaseViewModel<T extends BasePresenter> {
    void onStart();

    void onStop();

    void setPresenter(T presenter);
}
