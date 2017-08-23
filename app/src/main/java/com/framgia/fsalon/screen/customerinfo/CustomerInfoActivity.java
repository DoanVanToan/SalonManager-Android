package com.framgia.fsalon.screen.customerinfo;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.databinding.ActivityCustomerInfoBinding;
import com.framgia.fsalon.utils.Constant;

/**
 * Customerinfo Screen.
 */
public class CustomerInfoActivity extends AppCompatActivity {
    private CustomerInfoContract.ViewModel mViewModel;

    public static Intent getInstance(Context context, User user) {
        Intent intent = new Intent(context, CustomerInfoActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(Constant.BUNDLE_USER, user);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = getIntent().getExtras().getParcelable(Constant.BUNDLE_USER);
        mViewModel = new CustomerInfoViewModel(this, user);
        CustomerInfoContract.Presenter presenter =
            new CustomerInfoPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityCustomerInfoBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_customer_info);
        binding.setViewModel((CustomerInfoViewModel) mViewModel);
        setSupportActionBar(binding.toolbarCustomer);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(user.getName());
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
