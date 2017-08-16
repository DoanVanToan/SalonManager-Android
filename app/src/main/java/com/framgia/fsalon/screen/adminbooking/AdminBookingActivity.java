package com.framgia.fsalon.screen.adminbooking;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.databinding.ActivityAdminBookingBinding;
import com.framgia.fsalon.utils.Constant;

/**
 * Adminbooking Screen.
 */
public class AdminBookingActivity extends AppCompatActivity {
    private AdminBookingContract.ViewModel mViewModel;

    public static Intent getInstance(Context context, BookingOder bookingOder) {
        Intent intent = new Intent(context, AdminBookingActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(Constant.BUNDLE_ORDER, bookingOder);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new AdminBookingViewModel();
        AdminBookingContract.Presenter presenter =
            new AdminBookingPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityAdminBookingBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_admin_booking);
        binding.setViewModel((AdminBookingViewModel) mViewModel);
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
