package com.framgia.fsalon.screen.scheduler.detail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.source.BookingRepository;
import com.framgia.fsalon.data.source.UserDataSource;
import com.framgia.fsalon.data.source.UserRepository;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import com.framgia.fsalon.data.source.local.UserLocalDataSource;
import com.framgia.fsalon.data.source.local.sharepref.SharePreferenceImp;
import com.framgia.fsalon.data.source.remote.BookingRemoteDataSource;
import com.framgia.fsalon.data.source.remote.UserRemoteDataSource;
import com.framgia.fsalon.databinding.ActivityBookingDetailBinding;
import com.framgia.fsalon.screen.editstatusdialog.EditStatusDialogViewModel;

import static com.framgia.fsalon.utils.Constant.BOOKING_ID;

/**
 * Detail Screen.
 */
public class BookingDetailActivity extends AppCompatActivity
    implements EditStatusDialogViewModel.OnClickDialogListener {
    private BookingDetailContract.ViewModel mViewModel;
    private static final int DEFAULT_ID = 0;
    private int mIdBooking;
    private int mPermission;

    public static Intent getInstance(Context context, int id) {
        Intent intent = new Intent(context, BookingDetailActivity.class);
        intent.putExtra(BOOKING_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBundle();
        mViewModel = new BookingDetailViewModel(this, mIdBooking);
        UserDataSource.RemoteDataSource remoteDataSource =
            new UserRemoteDataSource(FSalonServiceClient.getInstance());
        UserDataSource.LocalDataSource localDataSource =
            new UserLocalDataSource(new SharePreferenceImp(this));
        BookingDetailContract.Presenter presenter =
            new BookingDetailPresenter(mViewModel, new BookingRepository(
                new BookingRemoteDataSource(FSalonServiceClient.getInstance())),
                new UserRepository(remoteDataSource, localDataSource));
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
        mViewModel.getBooking();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mViewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mViewModel.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRefresh() {
        mViewModel.onGetData();
    }

    private void getBundle() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        mIdBooking = intent.getIntExtra(BOOKING_ID, DEFAULT_ID);
    }
}