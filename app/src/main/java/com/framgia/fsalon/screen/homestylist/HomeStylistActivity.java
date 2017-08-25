package com.framgia.fsalon.screen.homestylist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.ActivityHomeStylistBinding;

/**
 * Homestylist Screen.
 */
public class HomeStylistActivity extends AppCompatActivity {
    private HomeStylistContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new HomeStylistViewModel(this);
        HomeStylistContract.Presenter presenter =
            new HomeStylistPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityHomeStylistBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_home_stylist);
        binding.setViewModel((HomeStylistViewModel) mViewModel);
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
