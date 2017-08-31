package com.framgia.fsalon.screen.report.billbookingreport;

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
import com.framgia.fsalon.databinding.FragmentBillBookingReportBinding;
import com.framgia.fsalon.utils.Constant;

/**
 * Billreport Screen.
 */
public class BillBookingReportFragment extends Fragment {
    private BillBookingReportContract.ViewModel mViewModel;

    public static BillBookingReportFragment newInstance(String type, int status, long start,
                                                        long end) {
        BillBookingReportFragment fragment = new BillBookingReportFragment();
        Bundle args = new Bundle();
        args.putString(Constant.Report.BUNDLE_TYPE, type);
        args.putLong(Constant.Report.BUNDLE_START, start);
        args.putLong(Constant.Report.BUNDLE_END, end);
        args.putInt(Constant.Report.BUNDLE_STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String type = getArguments().getString(Constant.Report.BUNDLE_TYPE);
        long start = getArguments().getLong(Constant.Report.BUNDLE_START);
        long end = getArguments().getLong(Constant.Report.BUNDLE_END);
        int status = getArguments().getInt(Constant.Report.BUNDLE_STATUS);
        mViewModel = new BillBookingReportViewModel(this);
        BillBookingReportContract.Presenter presenter =
            new BillBookingReportPresenter(mViewModel, new ReportRepository(new
                ReportRemoteDataSource(FSalonServiceClient.getInstance())), type, status, start,
                end);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentBillBookingReportBinding binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_bill_booking_report, container, false);
        binding.setViewModel((BillBookingReportViewModel) mViewModel);
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
