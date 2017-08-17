package com.framgia.fsalon.screen.customer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.source.UserRepository;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import com.framgia.fsalon.data.source.local.UserLocalDataSource;
import com.framgia.fsalon.data.source.local.sharepref.SharePreferenceImp;
import com.framgia.fsalon.data.source.remote.UserRemoteDataSource;
import com.framgia.fsalon.databinding.FragmentCustomerBinding;

/**
 * Customer Screen.
 */
public class CustomerFragment extends Fragment {
    private CustomerContract.ViewModel mViewModel;

    public static CustomerFragment newInstance() {
        return new CustomerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new CustomerViewModel(this, getActivity().getFragmentManager());
        CustomerContract.Presenter presenter =
            new CustomerPresenter(mViewModel,
                new UserRepository(new UserRemoteDataSource(FSalonServiceClient.getInstance()),
                    new UserLocalDataSource(new SharePreferenceImp(getContext()))));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentCustomerBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_customer, container, false);
        binding.setViewModel((CustomerViewModel) mViewModel);
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
