package com.framgia.fsalon.screen.home;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.ActivityHomeBinding;
import com.framgia.fsalon.utils.Utils;

/**
 * Home Screen.
 */
public class HomeActivity extends AppCompatActivity {
    private HomeContract.ViewModel mViewModel;

    public static Intent getInstance(Context context) {
        return new Intent(context, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new HomeViewModel(this);
        HomeContract.Presenter presenter = new HomePresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        final ActivityHomeBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setViewModel((HomeViewModel) mViewModel);
        Utils.disableShiftMode(binding.bottomUserHome);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
