package com.framgia.fsalon.screen.report.customerreport;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.source.ReportRepository;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import com.framgia.fsalon.data.source.remote.ReportRemoteDataSource;
import com.framgia.fsalon.databinding.FragmentCustomerReportBinding;
import com.framgia.fsalon.utils.Constant;

/**
 * Reportcustomer Screen.
 */
public class CustomerReportFragment extends Fragment {
    private CustomerReportContract.ViewModel mViewModel;

    public static CustomerReportFragment newInstance(String type, long start, long end) {
        CustomerReportFragment fragment = new CustomerReportFragment();
        Bundle args = new Bundle();
        args.putString(Constant.Report.BUNDLE_TYPE, type);
        args.putLong(Constant.Report.BUNDLE_START, start);
        args.putLong(Constant.Report.BUNDLE_END, end);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type = getArguments().getString(Constant.Report.BUNDLE_TYPE);
        long start = getArguments().getLong(Constant.Report.BUNDLE_START);
        long end = getArguments().getLong(Constant.Report.BUNDLE_END);
        mViewModel = new CustomerReportViewModel(this);
        CustomerReportContract.Presenter presenter =
            new CustomerReportPresenter(mViewModel, new ReportRepository(new
                ReportRemoteDataSource(FSalonServiceClient.getInstance())), type, start, end);
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
