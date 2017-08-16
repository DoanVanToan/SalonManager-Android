package com.framgia.fsalon.screen.customer;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.Customer;
import com.framgia.fsalon.databinding.ItemCustomerBinding;

import java.util.List;

/**
 * Created by MyPC on 27/07/2017.
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    private List<Customer> mData;
    private CustomerViewModel mViewModel;

    protected CustomerAdapter(@NonNull Context context, List<Customer> data,
                              CustomerViewModel viewModel) {
        mData = data;
        mViewModel = viewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCustomerBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_customer, parent, false);
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

    public void onUpdatePage(List<Customer> data) {
        if (data == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCustomerBinding mBinding;
        private CustomerViewModel mViewModel;

        public ViewHolder(ItemCustomerBinding binding, CustomerViewModel viewModel) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = viewModel;
        }

        void bindData(Customer customer) {
            if (customer == null) {
                return;
            }
            mBinding.setViewModel(mViewModel);
            mBinding.setCustomer(customer);
            mBinding.executePendingBindings();
        }
    }
}
