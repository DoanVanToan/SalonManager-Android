package com.framgia.fsalon.screen.success;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.databinding.ActivitySuccessBinding;
import com.framgia.fsalon.utils.Constant;

/**
 * Success Screen.
 */
public class SuccessActivity extends AppCompatActivity {
    private SuccessContract.ViewModel mViewModel;

    public static Intent getInstance(Context context, BookingOder bookingOder) {
        Intent intent = new Intent(context, SuccessActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(Constant.BUNDLE_ORDER, bookingOder);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        BookingOder order = bundle.getParcelable(Constant.BUNDLE_ORDER);
        mViewModel = new SuccessViewModel(this, order);
        SuccessContract.Presenter presenter =
            new SuccessPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivitySuccessBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_success);
        binding.setViewModel((SuccessViewModel) mViewModel);
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
