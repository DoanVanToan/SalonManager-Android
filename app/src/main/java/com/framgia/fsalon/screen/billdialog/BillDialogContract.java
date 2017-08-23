package com.framgia.fsalon.screen.billdialog;

import android.widget.ArrayAdapter;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.BillItemRequest;
import com.framgia.fsalon.data.model.Service;
import com.framgia.fsalon.data.model.Stylist;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface BillDialogContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void setStylistPosition(int pos);
        void setServicePosition(int pos);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        int getStylistPos(ArrayAdapter<Stylist> mStylistAdapter, BillItemRequest billItemRequest);
        int getServicePos(ArrayAdapter<Service> serviceAdapter, BillItemRequest billItemRequest);
    }
}
