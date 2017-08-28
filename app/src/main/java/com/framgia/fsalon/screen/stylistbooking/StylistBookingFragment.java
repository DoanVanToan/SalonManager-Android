package com.framgia.fsalon.screen.stylistbooking;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.source.ManageBookingRepository;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import com.framgia.fsalon.data.source.remote.ManageBookingRemoteDataSource;
import com.framgia.fsalon.databinding.FragmentStylistBookingBinding;

/**
 * StylistBooking Screen.
 */
public class StylistBookingFragment extends Fragment {
    private StylistBookingContract.ViewModel mViewModel;

    public static StylistBookingFragment newInstance() {
        return new StylistBookingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new StylistBookingViewModel(this);
        StylistBookingContract.Presenter presenter =
            new StylistBookingPresenter(mViewModel, new ManageBookingRepository(
                new ManageBookingRemoteDataSource(FSalonServiceClient.getInstance())));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentStylistBookingBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_stylist_booking, container, false);
        binding.setViewModel((StylistBookingViewModel) mViewModel);
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
