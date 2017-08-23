package com.framgia.fsalon.screen.billdialog;

import android.widget.ArrayAdapter;

import com.framgia.fsalon.data.model.BillItemRequest;
import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.Stylist;

/**
 * Listens to user actions from the UI ({@link BillDialogFragment}), retrieves the data and updates
 * the UI as required.
 */
public class BillDialogPresenter implements BillDialogContract.Presenter {
    private final BillDialogContract.ViewModel mViewModel;

    public BillDialogPresenter(BillDialogContract.ViewModel viewModel,
                               ArrayAdapter<Stylist> stylistAdapter,
                               ArrayAdapter<Service> serviceAdapter,
                               BillItemRequest itemRequest) {
        mViewModel = viewModel;
        mViewModel.setStylistPosition(getStylistPos(stylistAdapter, itemRequest));
        mViewModel.setServicePosition(getServicePos(serviceAdapter, itemRequest));
    }

    @Override
    public void onStart() {
    }


    @Override
    public void onStop() {
    }

    @Override
    public int getStylistPos(ArrayAdapter<Stylist> mStylistAdapter,
                             BillItemRequest billItemRequest) {
        for (int i = 0; i < mStylistAdapter.getCount(); i++) {
            if (mStylistAdapter.getItem(i).getId() == billItemRequest.getStylistId()) {
                mViewModel.setStylistPosition(i);
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getServicePos(ArrayAdapter<Service> serviceAdapter,
                             BillItemRequest billItemRequest) {
        for (int i = 0; i < serviceAdapter.getCount(); i++) {
            if (serviceAdapter.getItem(i).getId() == billItemRequest.getServiceProductId()) {
                mViewModel.setServicePosition(i);
                return i;
            }
        }
        return -1;
    }
}
