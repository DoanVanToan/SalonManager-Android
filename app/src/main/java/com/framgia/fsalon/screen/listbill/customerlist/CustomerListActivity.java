package com.framgia.fsalon.screen.listbill.customerlist;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.FSalonApplication;
import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.ActivityCustomerListBinding;
import com.framgia.fsalon.screen.customer.CustomerFragment;
import com.framgia.fsalon.utils.navigator.Navigator;

/**
 * CustomerList Screen.
 */
public class CustomerListActivity extends AppCompatActivity {
    private CustomerListContract.ViewModel mViewModel;
    private CustomerFragment mCustomerFragment;
    private Navigator mNavigator;

    public static Intent getInstance() {
        Intent intent = new Intent(FSalonApplication.getInstant(), CustomerListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new CustomerListViewModel();
        mNavigator = new Navigator(this);
        CustomerListContract.Presenter presenter =
            new CustomerListPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        initCustomerFragment();
        ActivityCustomerListBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_customer_list);
        binding.setViewModel((CustomerListViewModel) mViewModel);
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

    private void initCustomerFragment() {
        mCustomerFragment = CustomerFragment.newInstance(true);
        mNavigator.replaceFragmentToActivity(getSupportFragmentManager(), mCustomerFragment,
            R.id.linear_customer);
    }
}
