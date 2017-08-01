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
import com.framgia.fsalon.data.model.DateBooking;
import com.framgia.fsalon.databinding.ItemDateBookingBinding;

import java.util.List;

/**
 * Created by MyPC on 20/07/2017.
 */
public class DateBookingAdapter extends BaseRecyclerViewAdapter<DateBooking, DateBookingAdapter
    .ViewHolder> {
    private List<DateBooking> mData;
    private BookingViewModel mViewModel;
    private int mSelectedPosition = -1;

    protected DateBookingAdapter(@NonNull Context context, List<DateBooking> data,
                                 BookingViewModel viewModel) {
        super(context);
        mData = data;
        mViewModel = viewModel;
    }

    public DateBooking getItem(int position) {
        return position < 0 ? null : mData.get(position);
    }

    public void selectedPosition(int position) {
        if (position < 0) {
            return;
        }
        mSelectedPosition = position;
        notifyDataSetChanged();
    }

    @Override
    public void onUpdatePage(List<DateBooking> data) {
        if (data == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemDateBookingBinding binding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_date_booking, parent, false);
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
        private ItemDateBookingBinding mBinding;
        private BookingViewModel mViewModel;

        public ViewHolder(ItemDateBookingBinding binding, BookingViewModel viewModel) {
            super(binding.getRoot());
            mBinding = binding;
            mViewModel = viewModel;
        }

        void bindData(DateBooking dateBooking) {
            if (dateBooking == null) {
                return;
            }
            ViewHolderModel model =
                new ViewHolderModel(dateBooking, mViewModel, getAdapterPosition(),
                    mSelectedPosition == getAdapterPosition());
            mBinding.setViewHolderModel(model);
            mBinding.executePendingBindings();
        }
    }

    public class ViewHolderModel extends BaseObservable {
        private DateBooking mDateBooking;
        private BookingViewModel mViewModel;
        private int mPosition;
        private boolean mIsSelected;

        public ViewHolderModel(DateBooking dateBooking, BookingViewModel viewModel, int position,
                               boolean isSelected) {
            mDateBooking = dateBooking;
            mViewModel = viewModel;
            mPosition = position;
            mIsSelected = isSelected;
        }

        @Bindable
        public DateBooking getDateBooking() {
            return mDateBooking;
        }

        public void setDateBooking(DateBooking dateBooking) {
            mDateBooking = dateBooking;
            notifyPropertyChanged(BR.dateBooking);
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
