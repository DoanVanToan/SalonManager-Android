package com.framgia.fsalon.screen.billdialog;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BillItemRequest;
import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.databinding.FragmentBilldialogBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * BillDialog Screen.
 */
public class BillDialogFragment extends DialogFragment {
    private static final String STYLISTS = "mStylists";
    private static final String SERVICES = "mServices";
    private static final String ITEM_REQUEST = "item_request";
    private BillDialogContract.ViewModel mViewModel;
    private ArrayAdapter<Stylist> mStylistAdapter;
    private ArrayAdapter<Service> mServiceAdapter;
    private List<Stylist> mStylists;
    private List<Service> mServices;
    private BillItemRequest mBillItemRequest;
    private Fragment mFragment;
    BillDialogViewModel.OnClickDialogListener mListener;

    public BillDialogFragment() {
    }

    public static BillDialogFragment newInstance(List<Stylist> stylist,
                                                 List<Service> service,
                                                 BillItemRequest itemRequest) {
        BillDialogFragment fragment = new BillDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STYLISTS, (ArrayList<? extends Parcelable>) stylist);
        bundle.putParcelableArrayList(SERVICES, (ArrayList<? extends Parcelable>) service);
        bundle.putParcelable(ITEM_REQUEST, itemRequest);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void getData() {
        if (getArguments() != null) {
            mStylists = getArguments().getParcelableArrayList(STYLISTS);
            mServices = getArguments().getParcelableArrayList(SERVICES);
            mBillItemRequest = getArguments().getParcelable(ITEM_REQUEST);
            mFragment = this;
            mStylistAdapter = new ArrayAdapter<>(mFragment.getContext(), R.layout
                .item_spinner_small,
                mStylists);
            mServiceAdapter = new ArrayAdapter<>(mFragment.getContext(), R.layout
                .item_spinner_small,
                mServices);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (BillDialogViewModel.OnClickDialogListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        mViewModel = new BillDialogViewModel(mStylistAdapter, mServiceAdapter, mBillItemRequest,
            mFragment, mListener);
        BillDialogContract.Presenter presenter =
            new BillDialogPresenter(mViewModel, mStylistAdapter, mServiceAdapter, mBillItemRequest);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        FragmentBilldialogBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_billdialog,
                container, false);
        binding.setViewModel((BillDialogViewModel) mViewModel);
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
