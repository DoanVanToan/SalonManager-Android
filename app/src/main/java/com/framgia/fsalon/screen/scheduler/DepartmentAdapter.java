package com.framgia.fsalon.screen.scheduler;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.BaseRecyclerViewAdapter;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.Salon;
import com.framgia.fsalon.databinding.ItemDepartmentBinding;

import java.util.List;

/**
 * Created by MyPC on 08/08/2017.
 */
public class DepartmentAdapter
    extends BaseRecyclerViewAdapter<Salon, DepartmentAdapter.ViewHolder> {
    private List<Salon> mData;
    private SchedulerViewModel mViewModel;
    private int mSelectedPosition = -1;

    protected DepartmentAdapter(@NonNull Context context, List<Salon> data,
                                SchedulerViewModel viewModel) {
        super(context);
        mData = data;
        mViewModel = viewModel;
    }

    public void selectedPosition(int position) {
        if (position < 0) {
            return;
        }
        mSelectedPosition = position;
        notifyDataSetChanged();
    }

    public Salon getItem(int position) {
        return position < 0 ? null : mData.get(position);
    }

    @Override
    public void onUpdatePage(List<Salon> data) {
        if (data == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public DepartmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDepartmentBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_department, parent, false);
        return new DepartmentAdapter.ViewHolder(binding, mViewModel);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * ViewHolder
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemDepartmentBinding mBinding;
        private SchedulerViewModel mViewModel;

        public ViewHolder(ItemDepartmentBinding binding, SchedulerViewModel viewModel) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = viewModel;
        }

        void bindData(Salon salon) {
            if (salon == null) {
                return;
            }
            ViewHolderModel
                model = new ViewHolderModel(salon, mViewModel, getAdapterPosition(),
                mSelectedPosition == getAdapterPosition());
            mBinding.setViewHolderModel(model);
            mBinding.executePendingBindings();
        }
    }

    /**
     * ViewHolderModel
     */
    public class ViewHolderModel extends BaseObservable {
        private Salon mSalon;
        private SchedulerViewModel mViewModel;
        private int mPosition;
        private boolean mIsSelected;

        public ViewHolderModel(Salon salon, SchedulerViewModel viewModel, int position,
                               boolean isSelected) {
            mSalon = salon;
            mViewModel = viewModel;
            mPosition = position;
            mIsSelected = isSelected;
        }

        @Bindable
        public Salon getSalon() {
            return mSalon;
        }

        public void setSalon(Salon salon) {
            mSalon = salon;
            notifyPropertyChanged(BR.salon);
        }

        @Bindable
        public SchedulerViewModel getViewModel() {
            return mViewModel;
        }

        public void setViewModel(SchedulerViewModel viewModel) {
            mViewModel = viewModel;
            notifyPropertyChanged(BR.viewModel);
        }

        @Bindable
        public int getPosition() {
            return mPosition;
        }

        public void setPosition(int position) {
            mPosition = position;
            notifyItemChanged(BR.position);
        }

        @Bindable
        public boolean isSelected() {
            return mIsSelected;
        }

        public void setSelected(boolean selected) {
            mIsSelected = selected;
            notifyPropertyChanged(BR.selected);
        }
    }
}
