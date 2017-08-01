package com.framgia.fsalon.screen.detail;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

/**
 * Exposes the data to be used in the Detail screen.
 */
public class DetailViewModel implements DetailContract.ViewModel {
    private DetailContract.Presenter mPresenter;

    public DetailViewModel() {
    }

    public void setValueFromUser(){
        // TODO: 7/20/2017
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void onDetailClicked(View view){
        // TODO: 7/20/2017 switch to edit activity
    }
}
