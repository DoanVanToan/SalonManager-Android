package com.framgia.fsalon.screen.scheduler.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.source.BookingRepository;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import com.framgia.fsalon.data.source.remote.BookingRemoteDataSource;
import com.framgia.fsalon.databinding.ActivityBookingDetailBinding;
import com.framgia.fsalon.utils.Constant;
import com.framgia.fsalon.utils.Utils;

/**
 * Detail Screen.
 */
public class BookingDetailActivity extends AppCompatActivity {
    private BookingDetailContract.ViewModel mViewModel;
    private static final int DEFAULT_ID = 0;
    private static final int DEFAULT_STATUS = -1;

    public static Intent getInstance(Context context, int id) {
        Intent intent = new Intent(context, BookingDetailActivity.class);
        Bundle args = new Bundle();
        args.putInt(Constant.BOOKING_ID, id);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new BookingDetailViewModel(this, getIntent().getIntExtra(Constant
            .BOOKING_ID, DEFAULT_ID));
        BookingDetailContract.Presenter presenter =
            new BookingDetailPresenter(mViewModel, new BookingRepository(
                new BookingRemoteDataSource(FSalonServiceClient.getInstance())));
        mViewModel.setPresenter(presenter);
        ActivityBookingDetailBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_booking_detail);
        binding.setViewModel((BookingDetailViewModel) mViewModel);
        setSupportActionBar(binding.toolbarAdminBookingDetail);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_booking_detail);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbarAdminBookingDetail
            .setTitleTextColor(ContextCompat.getColor(this, R.color.color_white));
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
