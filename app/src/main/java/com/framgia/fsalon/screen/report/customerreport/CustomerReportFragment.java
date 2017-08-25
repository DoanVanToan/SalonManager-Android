package com.framgia.fsalon.screen.report.customerreport;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.FragmentCustomerReportBinding;

/**
 * Reportcustomer Screen.
 */
public class CustomerReportFragment extends Fragment {
    private CustomerReportContract.ViewModel mViewModel;

    public static CustomerReportFragment newInstance() {
        return new CustomerReportFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new CustomerReportViewModel(this);
        CustomerReportContract.Presenter presenter =
            new CustomerReportPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentCustomerReportBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_customer_report, container, false);
        binding.setViewModel((CustomerReportViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
