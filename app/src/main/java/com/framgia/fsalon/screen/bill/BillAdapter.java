package com.framgia.fsalon.screen.bill;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fsalon.BaseRecyclerViewAdapter;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BillItemRequest;
import com.framgia.fsalon.databinding.ItemBillBinding;

import java.util.List;

/**
 * Created by MyPC on 02/08/2017.
 */
public class BillAdapter extends BaseRecyclerViewAdapter<BillItemRequest, BillAdapter
    .ViewHolder> {
    private List<BillItemRequest> mData;
    private BillViewModel mViewModel;

    protected BillAdapter(@NonNull Context context, List<BillItemRequest> data,
                          BillViewModel viewModel) {
        super(context);
        mData = data;
        mViewModel = viewModel;
    }

    @Override
    public void onUpdatePage(List<BillItemRequest> data) {
        if (data == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBillBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_bill, parent, false);
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

    public void onUpdateAdapter(BillItemRequest bill) {
        BillItemRequest billItemRequest;
        if (bill == null) {
            return;
        }
        for (int pos = 0; pos < mData.size(); pos++) {
            billItemRequest = mData.get(pos);
            if (billItemRequest.getServiceName().equals(bill.getServiceName())
                && billItemRequest.getStylistName().equals(bill.getStylistName())) {
                onUpdateItem(pos, mData.get(pos));
                return;
            }
        }
        onAddItem(bill);
    }

    public void onUpdateBillItem(BillItemRequest newBill, BillItemRequest oldBill) {
        if (newBill == null) {
            return;
        }
        int pos = mData.indexOf(oldBill);
        mData.set(pos, newBill);
        notifyDataSetChanged();
    }

    private void onAddItem(BillItemRequest bill) {
        if (bill == null) {
            return;
        }
        mData.add(bill);
        notifyDataSetChanged();
    }

    public void onDeleteItem(int position) {
        if (position < 0 || position > mData.size() - 1) {
            return;
        }
        mData.remove(position);
        notifyDataSetChanged();
    }

    public List<BillItemRequest> getData() {
        return mData;
    }

    public float getTotalPrice() {
        float total = 0;
        for (BillItemRequest request : mData) {
            total += request.getPrice() * request.getQty();
        }
        return total;
    }

    private void onUpdateItem(int postion, BillItemRequest bill) {
        if (postion < 0) {
            return;
        }
        bill.setQty(bill.getQty() + 1);
        notifyItemChanged(postion, bill);
    }

    /**
     * ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemBillBinding mBinding;
        private BillViewModel mViewModel;

        public ViewHolder(ItemBillBinding binding, BillViewModel viewModel) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = viewModel;
        }

        void bindData(BillItemRequest bill) {
            if (bill == null) {
                return;
            }
            mBinding.setViewModel(mViewModel);
            mBinding.setItem(bill);
            mBinding.setPosition(getAdapterPosition());
            mBinding.executePendingBindings();
        }
    }
}