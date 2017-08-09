package com.framgia.fsalon.screen.bill;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.source.BillRepository;
import com.framgia.fsalon.data.source.BookingRepository;
import com.framgia.fsalon.data.source.SalonRepository;
import com.framgia.fsalon.data.source.ServiceRepository;
import com.framgia.fsalon.data.source.StylistRepository;
import com.framgia.fsalon.data.source.UserRepository;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import com.framgia.fsalon.data.source.local.UserLocalDataSource;
import com.framgia.fsalon.data.source.local.sharepref.SharePreferenceImp;
import com.framgia.fsalon.data.source.remote.BillRemoteDataSource;
import com.framgia.fsalon.data.source.remote.BookingRemoteDataSource;
import com.framgia.fsalon.data.source.remote.SalonRemoteDataSource;
import com.framgia.fsalon.data.source.remote.ServiceRemoteDataSource;
import com.framgia.fsalon.data.source.remote.StylistRemoteDataSource;
import com.framgia.fsalon.data.source.remote.UserRemoteDataSource;
import com.framgia.fsalon.databinding.ActivityBillBinding;

/**
 * BillItemRequest Screen.
 */
public class BillActivity extends AppCompatActivity {
    private BillContract.ViewModel mViewModel;

    public static Intent getInstance(Context context) {
        Intent intent = new Intent(context, BillActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new BillViewModel(this);
        BillContract.Presenter presenter =
            new BillPresenter(mViewModel, new StylistRepository(new StylistRemoteDataSource(
                FSalonServiceClient.getInstance())), new ServiceRepository(
                new ServiceRemoteDataSource(FSalonServiceClient.getInstance())),
                new BillRepository(new BillRemoteDataSource(FSalonServiceClient.getInstance())),
                new BookingRepository(
                    new BookingRemoteDataSource(FSalonServiceClient.getInstance())),
                new SalonRepository(new SalonRemoteDataSource(FSalonServiceClient.getInstance())),
                new UserRepository(new UserRemoteDataSource(FSalonServiceClient.getInstance()),
                    new UserLocalDataSource(new SharePreferenceImp(this))));
        mViewModel.setPresenter(presenter);
        ActivityBillBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_bill);
        binding.setViewModel((BillViewModel) mViewModel);
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
