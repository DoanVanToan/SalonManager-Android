package com.framgia.fsalon.screen.listbill;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.FragmentListbillBinding;

/**
 * Listbill Screen.
 */
public class ListBillFragment extends Fragment {
    private ListBillContract.ViewModel mViewModel;

    public static ListBillFragment newInstance() {
        return new ListBillFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ListBillViewModel(getActivity());
        ListBillContract.Presenter presenter =
            new ListBillPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentListbillBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_listbill, container, false);
        binding.setViewModel((ListBillViewModel) mViewModel);
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
