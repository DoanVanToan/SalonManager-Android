package com.framgia.fsalon.screen.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.ActivityUserBinding;

/**
 * User Screen.
 */
public class UserActivity extends AppCompatActivity {
    private UserContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new UserViewModel();
        UserContract.Presenter presenter =
            new UserPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityUserBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_user);
        binding.setViewModel((UserViewModel) mViewModel);
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
