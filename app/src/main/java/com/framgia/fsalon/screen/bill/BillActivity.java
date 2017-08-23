package com.framgia.fsalon.screen.bill;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BillItemRequest;
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
import com.framgia.fsalon.screen.billdialog.BillDialogViewModel;
import com.framgia.fsalon.utils.Constant;

/**
 * BillItemRequest Screen.
 */
public class BillActivity extends AppCompatActivity
    implements BillDialogViewModel.OnClickDialogListener {
    private BillContract.ViewModel mViewModel;

    public static Intent getInstance(Context context, int billId) {
        Intent intent = new Intent(context, BillActivity.class);
        Bundle args = new Bundle();
        args.putInt(Constant.BUNDLE_BILL_ID, billId);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new BillViewModel(this);
        int billId = getIntent().getExtras().getInt(Constant.BUNDLE_BILL_ID);
        BillContract.Presenter presenter =
            new BillPresenter(mViewModel, billId, new StylistRepository(new StylistRemoteDataSource(
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

    @Override
    public void onDialogCancelClick(DialogFragment dialogFragment) {
        dialogFragment.dismiss();
    }

    @Override
    public void onDialogUpdateClick(BillItemRequest newBill, BillItemRequest oldBill) {
        if (newBill == null || oldBill == null) {
            return;
        }
        ((BillViewModel) mViewModel).onUpdateItemBill(newBill, oldBill);
    }
}
