package com.framgia.fsalon.screen.editbooking;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.databinding.ActivityEditBookingBinding;
import com.framgia.fsalon.screen.booking.BookingFragment;
import com.framgia.fsalon.utils.Constant;

/**
 * Editbooking Screen.
 */
public class EditBookingActivity extends AppCompatActivity {
    private EditBookingContract.ViewModel mViewModel;

    public static Intent getInstance(Context context, BookingOder bookingOder) {
        Intent intent = new Intent(context, EditBookingActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(Constant.BUNDLE_ORDER, bookingOder);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new EditBookingViewModel();
        EditBookingContract.Presenter presenter =
            new EditBookingPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityEditBookingBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_edit_booking);
        binding.setViewModel((EditBookingViewModel) mViewModel);
        BookingFragment fragment = BookingFragment.newInstance((BookingOder) getIntent()
            .getExtras().getParcelable(Constant.BUNDLE_ORDER));
        getSupportFragmentManager().beginTransaction().add(R.id.frame_edit, fragment).commit();
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
