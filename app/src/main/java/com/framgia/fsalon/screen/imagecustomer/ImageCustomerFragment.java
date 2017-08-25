package com.framgia.fsalon.screen.imagecustomer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.source.BillRepository;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import com.framgia.fsalon.data.source.remote.BillRemoteDataSource;
import com.framgia.fsalon.databinding.FragmentImageCustomerBinding;

import static com.framgia.fsalon.utils.Constant.OUT_OF_INDEX;

/**
 * Imagecustomer Screen.
 */
public class ImageCustomerFragment extends Fragment {
    private ImageCustomerContract.ViewModel mViewModel;
    private static final String BUNDLE_CUSTOMER_ID = "BUNDLE_CUSTOMER_ID";

    public static ImageCustomerFragment newInstance(int customerId) {
        ImageCustomerFragment fragment = new ImageCustomerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_CUSTOMER_ID, customerId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int customerId = OUT_OF_INDEX;
        if (getArguments() != null) {
            customerId = getArguments().getInt(BUNDLE_CUSTOMER_ID);
        }
        mViewModel = new ImageCustomerViewModel(this);
        ImageCustomerContract.Presenter presenter = new ImageCustomerPresenter(mViewModel,
            new BillRepository(new BillRemoteDataSource(FSalonServiceClient.getInstance())),
            customerId);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentImageCustomerBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_image_customer, container, false);
        binding.setViewModel((ImageCustomerViewModel) mViewModel);
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
