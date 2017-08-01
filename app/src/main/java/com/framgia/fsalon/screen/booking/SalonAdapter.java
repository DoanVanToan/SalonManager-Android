package com.framgia.fsalon.screen.booking;

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
import com.framgia.fsalon.databinding.ItemSalonBinding;

import java.util.List;

/**
 * Created by MyPC on 20/07/2017.
 */
public class SalonAdapter extends BaseRecyclerViewAdapter<Salon, SalonAdapter.ViewHolder> {
    private List<Salon> mData;
    private BookingViewModel mViewModel;
    private int mSelectedPosition = -1;

    protected SalonAdapter(@NonNull Context context, List<Salon> data,
                           BookingViewModel viewModel) {
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemSalonBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_salon, parent, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSalonBinding mBinding;
        private BookingViewModel mViewModel;

        public ViewHolder(ItemSalonBinding binding, BookingViewModel viewModel) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = viewModel;
        }

        void bindData(Salon salon) {
            if (salon == null) {
                return;
            }
            ViewHolderModel model = new ViewHolderModel(salon, mViewModel, getAdapterPosition(),
                mSelectedPosition == getAdapterPosition());
            mBinding.setViewHolderModel(model);
            mBinding.executePendingBindings();
        }
    }

    public class ViewHolderModel extends BaseObservable {
        private Salon mSalon;
        private BookingViewModel mViewModel;
        private int mPosition;
        private boolean mIsSelected;

        public ViewHolderModel(Salon salon, BookingViewModel viewModel, int position,
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
        public BookingViewModel getViewModel() {
            return mViewModel;
        }

        public void setViewModel(BookingViewModel viewModel) {
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
