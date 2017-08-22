package com.framgia.fsalon.screen.customerinfo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.ActivityCustomerInfoBinding;

/**
 * Customerinfo Screen.
 */
public class CustomerInfoActivity extends AppCompatActivity {
    private CustomerInfoContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new CustomerInfoViewModel(this);
        CustomerInfoContract.Presenter presenter =
            new CustomerInfoPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityCustomerInfoBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_customer_info);
        binding.setViewModel((CustomerInfoViewModel) mViewModel);
        setSupportActionBar(binding.toolbarCustomer);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_customer);
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
}
