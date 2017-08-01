package com.framgia.fsalon.screen.homeadmin;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.ActivityAdminHomeBinding;

/**
 * Adminhome Screen.
 */
public class AdminHomeActivity extends AppCompatActivity {
    private AdminHomeContract.ViewModel mViewModel;

    public static Intent getInstance(Context context) {
        return new Intent(context, AdminHomeActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new AdminHomeViewModel(this);
        AdminHomeContract.Presenter presenter =
            new AdminHomePresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityAdminHomeBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_admin_home);
        binding.setViewModel((AdminHomeViewModel) mViewModel);
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
