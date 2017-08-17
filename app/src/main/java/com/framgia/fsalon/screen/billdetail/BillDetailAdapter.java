package com.framgia.fsalon.screen.billdetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fsalon.BaseRecyclerViewAdapter;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BillItemResponse;
import com.framgia.fsalon.databinding.ItemDetailBillBinding;

import java.util.List;

/**
 * Created by MyPC on 16/08/2017.
 */
public class BillDetailAdapter
    extends BaseRecyclerViewAdapter<BillItemResponse, BillDetailAdapter.ViewHolder> {
    private List<BillItemResponse> mData;
    private BillDetailViewModel mViewModel;

    protected BillDetailAdapter(@NonNull Context context, List<BillItemResponse> data,
                                BillDetailViewModel viewModel) {
        super(context);
        mData = data;
        mViewModel = viewModel;
    }

    @Override
    public void onUpdatePage(List<BillItemResponse> data) {
        if (data == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDetailBillBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_detail_bill, parent, false);
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

    public List<BillItemResponse> getData() {
        return mData;
    }

    public float getTotalPrice() {
        float total = 0;
        for (BillItemResponse request : mData) {
            total += request.getPrice() * request.getQty();
        }
        return total;
    }

    /**
     * ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemDetailBillBinding mBinding;
        private BillDetailViewModel mViewModel;

        public ViewHolder(ItemDetailBillBinding binding, BillDetailViewModel viewModel) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = viewModel;
        }

        void bindData(BillItemResponse bill) {
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
