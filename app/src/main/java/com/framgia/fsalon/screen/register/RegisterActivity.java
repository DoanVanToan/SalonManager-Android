package com.framgia.fsalon.screen.register;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;

import com.framgia.fsalon.databinding.ActivityRegisterBinding;

/**
 * Register Screen.
 */
public class RegisterActivity extends AppCompatActivity {
    private RegisterContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new RegisterViewModel();
        RegisterContract.Presenter presenter =
            new RegisterPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityRegisterBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setViewModel((RegisterViewModel) mViewModel);
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
