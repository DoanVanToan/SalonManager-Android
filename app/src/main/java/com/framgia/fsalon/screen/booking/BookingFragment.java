package com.framgia.fsalon.screen.booking;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.source.BookingRepository;
import com.framgia.fsalon.data.source.SalonRepository;
import com.framgia.fsalon.data.source.StylistRepository;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import com.framgia.fsalon.data.source.remote.BookingRemoteDataSource;
import com.framgia.fsalon.data.source.remote.SalonRemoteDataSource;
import com.framgia.fsalon.data.source.remote.StylistRemoteDataSource;
import com.framgia.fsalon.databinding.FragmentBookingBinding;

/**
 * Booking Screen.
 */
public class BookingFragment extends Fragment {
    private BookingContract.ViewModel mViewModel;

    public static BookingFragment newInstance() {
        return new BookingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new BookingViewModel(getActivity());
        BookingContract.Presenter presenter =
            new BookingPresenter(mViewModel, new BookingRepository(new BookingRemoteDataSource(
                FSalonServiceClient.getInstance())), new SalonRepository(new SalonRemoteDataSource
                (FSalonServiceClient.getInstance())), new StylistRepository(new
                StylistRemoteDataSource(FSalonServiceClient.getInstance())));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentBookingBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_booking, container, false);
        binding.setViewModel((BookingViewModel) mViewModel);
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