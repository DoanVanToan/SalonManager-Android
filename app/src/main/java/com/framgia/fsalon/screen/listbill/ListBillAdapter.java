package com.framgia.fsalon.screen.listbill;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BillResponse;
import com.framgia.fsalon.data.model.ListBillRespond;
import com.framgia.fsalon.databinding.ItemContentListBillBinding;
import com.framgia.fsalon.databinding.ItemHeaderListBillBinding;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STATUS_CANCEL;
import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STATUS_COMPLETED;
import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STATUS_WAITING;
import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STT_CANCEL;
import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STT_COMPLETED;
import static com.framgia.fsalon.screen.listbill.ListBillViewModel.STT_WAITING;

/**
 * Created by beepi on 10/08/2017.
 */
public class ListBillAdapter extends SectioningAdapter {
    private List<ListBillRespond> mSections = new ArrayList<>();
    private OnBillItemClick mOnBillItemClick;

    public ListBillAdapter(List<ListBillRespond> sections, OnBillItemClick onBillItemClick) {
        mSections = sections;
        mOnBillItemClick = onBillItemClick;
    }

    public void updateData(List<ListBillRespond> sections) {
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
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent,
                                                 int itemUserType) {
        ItemContentListBillBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()), R.layout.item_content_list_bill, parent,
            false);
        binding.setListenner(mOnBillItemClick);
        return new ItemViewHolder(binding);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent,
                                                     int headerUserType) {
        ItemHeaderListBillBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent
            .getContext()), R.layout.item_header_list_bill, parent, false);
        return new HeaderViewHolder(binding);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex,
                                     int itemIndex, int itemUserType) {
        ((ItemViewHolder) viewHolder).bind(mSections.get(sectionIndex).getListBill()
            .get(itemIndex));
    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder,
                                       int sectionIndex, int headerUserType) {
        ((HeaderViewHolder) viewHolder).bind(mSections.get(sectionIndex));
    }

    @Override
    public int getNumberOfSections() {
        return mSections != null ? mSections.size() : 0;
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return mSections.get(sectionIndex).getListBill().size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    /**
     * Make item holder for list bills
     */
    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder {
        private final ItemContentListBillBinding mContentBinding;

        public ItemViewHolder(ItemContentListBillBinding binding) {
            super(binding.getRoot());
            mContentBinding = binding;
        }

        private void bind(BillResponse bill) {
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
            mContentBinding.setBill(bill);
            mContentBinding.setListenner(mOnBillItemClick);
            mContentBinding.executePendingBindings();
        }
    }

    /**
     * make item header for list bills
     */
    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        private final ItemHeaderListBillBinding mHeaderBinding;

        public HeaderViewHolder(ItemHeaderListBillBinding bind) {
            super(bind.getRoot());
            mHeaderBinding = bind;
        }

        private void bind(ListBillRespond section) {
            if (section == null) {
                return;
            }
            mHeaderBinding.setSection(section);
            mHeaderBinding.executePendingBindings();
        }
    }

    /**
     * To use with many ViewModel
     */
    public interface OnBillItemClick {
        void onBillDetailClick(BillResponse bill);
    }
}
