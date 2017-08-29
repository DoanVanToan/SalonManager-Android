package com.framgia.fsalon.screen.editstatusdialog;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;

import com.android.databinding.library.baseAdapters.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.Status;
import com.framgia.fsalon.utils.navigator.Navigator;

import java.util.List;

/**
 * Exposes the data to be used in the Editstatusdialog screen.
 */
public class EditStatusDialogViewModel extends BaseObservable
    implements EditStatusDialogContract.ViewModel {
    private EditStatusDialogContract.Presenter mPresenter;
    private ArrayAdapter<Status> mStatusAdapter;
    private DialogFragment mDialogFragment;
    private Status mStatus;
    private Navigator mNavigator;
    private OnClickDialogListener mListener;

    /**
     * OnClickDialogListener
     */
    public interface OnClickDialogListener {
        void onRefresh();
    }

    public EditStatusDialogViewModel(DialogFragment dialogFragment,
                                     OnClickDialogListener listener) {
        mDialogFragment = dialogFragment;
        mListener = listener;
        mNavigator = new Navigator(mDialogFragment.getActivity());
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(EditStatusDialogContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onChangeStatusClick() {
        if (mStatus == null) {
            return;
        }
        mPresenter.changeStatusBooking(mStatus.getId());
    }

    @Override
    public void onCancelClick() {
        mDialogFragment.dismiss();
    }

    @Override
    public void onGetStatusesSuccess(List<Status> statuses) {
        mStatusAdapter =
            new ArrayAdapter<>(mDialogFragment.getContext(),
                R.layout.support_simple_spinner_dropdown_item, statuses);
    }

    @Override
    public void onChangeStatusSuccess(String s) {
        mNavigator.showToast(s);
        mListener.onRefresh();
        mDialogFragment.dismiss();
    }

    @Override
    public void onError(String message) {
        mNavigator.showToast(message);
        mDialogFragment.dismiss();
    }

    @Bindable
    public ArrayAdapter<Status> getStatusAdapter() {
        return mStatusAdapter;
    }

    public void setStatusAdapter(ArrayAdapter<Status> statusAdapter) {
        mStatusAdapter = statusAdapter;
        notifyPropertyChanged(BR.statusAdapter);
    }

    @Bindable
    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        mStatus = status;
        notifyPropertyChanged(BR.status);
    }
}
