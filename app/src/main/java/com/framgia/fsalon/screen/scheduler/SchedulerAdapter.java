package com.framgia.fsalon.screen.scheduler;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.ManageBookingResponse;
import com.framgia.fsalon.databinding.ItemContentSchedulerBinding;
import com.framgia.fsalon.databinding.ItemHeaderSchedulerBinding;
import com.framgia.fsalon.utils.Utils;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.framgia.fsalon.utils.Constant.NO_SCROLL;

/**
 * Created by beepi on 28/07/2017.
 */
public class SchedulerAdapter extends SectioningAdapter {
    private static final int HEADER = -1;
    private static final int GHOST_HEADER = -1;
    private List<ManageBookingResponse> mSections = new ArrayList<>();
    private SchedulerContract.ViewModel mViewModel;
    private List<BookingOder> mBookingOders = new ArrayList<>();

    public SchedulerAdapter(SchedulerContract.ViewModel viewModel, List<ManageBookingResponse>
        sections) {
        mSections = sections;
        mViewModel = viewModel;
    }

    public void updateData(List<ManageBookingResponse> sections) {
        if (sections == null) return;
        mSections.addAll(sections);
        notifyAllSectionsDataSetChanged();
    }

    public void clear() {
        mSections.clear();
        mBookingOders.clear();
        notifyAllSectionsDataSetChanged();
    }

    public int getItemWithNearestTime() {
        for (ManageBookingResponse manageBookingResponse : mSections) {
            mBookingOders.add(new BookingOder(new Date(HEADER)));
            mBookingOders.add(new BookingOder(new Date(GHOST_HEADER)));
            mBookingOders.addAll(manageBookingResponse.getListBook());
        }
        for (int i = 0; i < mBookingOders.size(); i++) {
            if (mBookingOders.get(i).getTimeStart().getTime() >= System.currentTimeMillis()) {
                return i;
            }
        }
        return NO_SCROLL;
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent,
                                                 int itemUserType) {
        ItemContentSchedulerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent
            .getContext()), R.layout.item_content_scheduler, parent, false);
        binding.setViewModel(mViewModel);
        return new ItemViewHolder(binding);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent,
                                                     int headerUserType) {
        ItemHeaderSchedulerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent
            .getContext()), R.layout.item_header_scheduler, parent, false);
        return new HeaderViewHolder(binding);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder,
                                     int sectionIndex,
                                     int itemIndex, int itemUserType) {
        ((ItemViewHolder) viewHolder).bind(mSections.get(sectionIndex).getListBook()
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
        return mSections.get(sectionIndex).getListBook().size();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder {
        private final ItemContentSchedulerBinding mContentBinding;

        public ItemViewHolder(ItemContentSchedulerBinding binding) {
            super(binding.getRoot());
            mContentBinding = binding;
        }

        private void bind(BookingOder bookingOrder) {
            if (bookingOrder == null) {
                return;
            }
            mContentBinding.setOrder(bookingOrder);
            mContentBinding.setTimeBook(Utils.convertTime(bookingOrder.getTimeStart()));
            mContentBinding.executePendingBindings();
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        private final ItemHeaderSchedulerBinding mHeaderBinding;

        public HeaderViewHolder(ItemHeaderSchedulerBinding bind) {
            super(bind.getRoot());
            mHeaderBinding = bind;
        }

        private void bind(ManageBookingResponse section) {
            if (section == null) {
                return;
            }
            mHeaderBinding.setSection(section);
            mHeaderBinding.executePendingBindings();
        }
    }
}
