package com.framgia.fsalon.screen.scheduler;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.FragmentSchedulerBinding;

/**
 * Scheduler Screen.
 */
public class SchedulerFragment extends Fragment {
    private SchedulerContract.ViewModel mViewModel;

    public static SchedulerFragment newInstance() {
        return new SchedulerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new SchedulerViewModel(this);
        SchedulerContract.Presenter presenter =
            new SchedulerPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentSchedulerBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_scheduler, container, false);
        binding.setViewModel((SchedulerViewModel) mViewModel);
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
