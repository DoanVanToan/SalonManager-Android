package com.framgia.fsalon.screen.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.ActivityDetailBinding;

/**
 * Detail Screen.
 */
public class DetailActivity extends AppCompatActivity {
    private DetailContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new DetailViewModel();
        DetailContract.Presenter presenter =
            new DetailPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityDetailBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail);
        binding.setViewModel((DetailViewModel) mViewModel);
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
