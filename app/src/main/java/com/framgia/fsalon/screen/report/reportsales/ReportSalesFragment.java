package com.framgia.fsalon.screen.report.reportsales;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.FragmentReportsalesBinding;
import com.github.mikephil.charting.animation.Easing;

/**
 * ReportSales Screen.
 */
public class ReportSalesFragment extends Fragment {
    private ReportSalesContract.ViewModel mViewModel;

    public static ReportSalesFragment newInstance() {
        return new ReportSalesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ReportSalesViewModel();
        ReportSalesContract.Presenter presenter =
            new ReportSalesPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentReportsalesBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_reportsales, container, false);
        binding.setViewModel((ReportSalesViewModel) mViewModel);
        setLineChart(binding);
        return binding.getRoot();
    }

    public void setLineChart(FragmentReportsalesBinding binding) {
        binding.lineChart.setTouchEnabled(true);
        binding.lineChart.setDragEnabled(true);
        binding.lineChart.setScaleEnabled(true);
        binding.lineChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);
        binding.lineChart.invalidate();
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
