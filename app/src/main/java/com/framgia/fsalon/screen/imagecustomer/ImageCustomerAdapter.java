package com.framgia.fsalon.screen.imagecustomer;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.codewaves.stickyheadergrid.StickyHeaderGridAdapter;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.data.model.ImageResponse;
import com.framgia.fsalon.databinding.ItemContentListImageBinding;
import com.framgia.fsalon.databinding.ItemHeaderListImageBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 23/08/2017.
 */
public class ImageCustomerAdapter extends StickyHeaderGridAdapter {
    private List<BillResponse> mSections = new ArrayList<>();
    private OnImageItemClick mOnImageItemClick;

    public ImageCustomerAdapter(List<BillResponse> sections,
                                OnImageItemClick onImageItemClick) {
        mSections = sections;
        mOnImageItemClick = onImageItemClick;
    }

    public void updateData(List<BillResponse> sections) {
        if (sections == null) {
            return;
        }
        mSections.addAll(sections);
        notifyAllSectionsDataSetChanged();
    }

    public void clear() {
        mSections.clear();
        notifyAllSectionsDataSetChanged();
    }

    @Override
    public int getSectionCount() {
        return mSections != null ? mSections.size() : 0;
    }

    @Override
    public int getSectionItemCount(int section) {
        if (mSections.get(section).getBookingOder() == null
            || mSections.get(section).getBookingOder().getImages() == null) {
            return 0;
        }
        return mSections.get(section).getBookingOder().getImages().size();
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        ItemHeaderListImageBinding binding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_header_list_image,
                parent, false);
        return new HeaderViewHolder(binding);
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        ItemContentListImageBinding binding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_content_list_image,
                parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindHeaderViewHolder(StickyHeaderGridAdapter.HeaderViewHolder viewHolder,
                                       int section) {
        ((HeaderViewHolder) viewHolder).bind(mSections.get(section));
    }

    @Override
    public void onBindItemViewHolder(StickyHeaderGridAdapter.ItemViewHolder viewHolder, int section,
                                     int offset) {
        ((ItemViewHolder) viewHolder)
            .bind(mSections.get(section).getBookingOder().getImages().get(offset));
    }

    /**
     * HeaderViewHolder
     */
    public class HeaderViewHolder extends StickyHeaderGridAdapter.HeaderViewHolder {
        private ItemHeaderListImageBinding mBinding;

        public HeaderViewHolder(ItemHeaderListImageBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(BillResponse billResponse) {
            mBinding.setSection(billResponse);
            mBinding.executePendingBindings();
        }
    }

    /**
     * ItemViewHolder
     */
    public class ItemViewHolder extends StickyHeaderGridAdapter.ItemViewHolder {
        private ItemContentListImageBinding mBinding;

        public ItemViewHolder(ItemContentListImageBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        private void bind(ImageResponse image) {
            mBinding.setImage(image);
            mBinding.executePendingBindings();
        }
    }

    /**
     * To use with many ViewModel
     */
    public interface OnImageItemClick {
    }
}
