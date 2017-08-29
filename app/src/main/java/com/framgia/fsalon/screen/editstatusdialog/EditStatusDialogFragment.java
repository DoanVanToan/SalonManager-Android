package com.framgia.fsalon.screen.editstatusdialog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.framgia.fsalon.R;
import com.framgia.fsalon.databinding.FragmentEditStatusDialogBinding;

import static com.framgia.fsalon.utils.Constant.OUT_OF_INDEX;

/**
 * Editstatusdialog Screen.
 */
public class EditStatusDialogFragment extends DialogFragment {
    private static final String BOOKING_ID = "BOOKING_ID";
    private static final String STATUS_ID = "STATUS_ID";
    private EditStatusDialogContract.ViewModel mViewModel;

    public static EditStatusDialogFragment newInstance(int bookingId, int statusId) {
        EditStatusDialogFragment fragment = new EditStatusDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BOOKING_ID, bookingId);
        bundle.putInt(STATUS_ID, statusId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int bookingId = OUT_OF_INDEX;
        int statusId = OUT_OF_INDEX;
        if (getArguments() != null) {
            bookingId = getArguments().getInt(BOOKING_ID);
            statusId = getArguments().getInt(STATUS_ID);
        }
        mViewModel = new EditStatusDialogViewModel(this);
        EditStatusDialogContract.Presenter presenter =
            new EditStatusDialogPresenter(mViewModel, bookingId, statusId);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        FragmentEditStatusDialogBinding binding =
            DataBindingUtil
                .inflate(inflater, R.layout.fragment_edit_status_dialog, container, false);
        binding.setViewModel((EditStatusDialogViewModel) mViewModel);
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
