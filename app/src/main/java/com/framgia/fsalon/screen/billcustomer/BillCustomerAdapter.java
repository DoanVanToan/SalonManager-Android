package com.framgia.fsalon.screen.billcustomer;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.databinding.ItemBillCustomerBinding;

import java.util.List;

import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STATUS_CANCEL;
import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STATUS_COMPLETED;
import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STATUS_WAITING;
import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STT_CANCEL;
import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STT_COMPLETED;
import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STT_WAITING;

/**
 * Created by MyPC on 24/08/2017.
 */
public class BillCustomerAdapter extends RecyclerView.Adapter<BillCustomerAdapter.ViewHolder> {
    private List<BillResponse> mData;
    private BillCustomerViewModel mViewModel;

    protected BillCustomerAdapter(@NonNull Context context, List<BillResponse> data,
                                  BillCustomerViewModel viewModel) {
        mData = data;
        mViewModel = viewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBillCustomerBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_bill_customer, parent, false);
        return new ViewHolder(binding, mViewModel);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void onUpdatePage(List<BillResponse> data) {
        if (data == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    /**
     * ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemBillCustomerBinding mBinding;
        private BillCustomerViewModel mViewModel;

        public ViewHolder(ItemBillCustomerBinding binding, BillCustomerViewModel viewModel) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = viewModel;
        }

        void bindData(BillResponse bill) {
            if (bill == null) {
                return;
            }
            switch (bill.getStatus()) {
                case STATUS_WAITING:
                    bill.setStatusName(STT_WAITING);
                    bill.setStatusColor(R.color.colorPrimary);
                    break;
                case STATUS_COMPLETED:
                    bill.setStatusName(STT_COMPLETED);
                    bill.setStatusColor(R.color.color_green_300);
                    break;
                case STATUS_CANCEL:
                    bill.setStatusName(STT_CANCEL);
                    bill.setStatusColor(R.color.color_red);
                    break;
            }
            mBinding.setViewModel(mViewModel);
            mBinding.setBill(bill);
            mBinding.executePendingBindings();
        }
    }
}
