package com.framgia.fsalon.screen.scheduler;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.Scheduler;
import com.framgia.fsalon.data.model.SchedulerSection;
import com.framgia.fsalon.databinding.ItemContentSchedulerBinding;
import com.framgia.fsalon.databinding.ItemHeaderSchedulerBinding;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beepi on 28/07/2017.
 */
public class SchedulerAdapter extends SectioningAdapter {
    private List<SchedulerSection> mSections = new ArrayList<>();

    public SchedulerAdapter(List<SchedulerSection> sections) {
        mSections = sections;
    }

    public void updateData(List<SchedulerSection> sections) {
        if (sections == null) return;
        mSections.clear();
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
        ItemContentSchedulerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent
            .getContext()), R.layout.item_content_scheduler, parent, false);
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
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex,
                                     int itemIndex, int itemUserType) {
        ((ItemViewHolder) viewHolder).bind(mSections.get(sectionIndex).getSections().get
            (itemIndex));
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
        return mSections.get(sectionIndex).getSections().size();
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

        private void bind(Scheduler scheduler) {
            if (scheduler == null) return;
            mContentBinding.setScheduler(scheduler);
            mContentBinding.executePendingBindings();
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder {
        private final ItemHeaderSchedulerBinding mHeaderBinding;

        public HeaderViewHolder(ItemHeaderSchedulerBinding bind) {
            super(bind.getRoot());
            mHeaderBinding = bind;
        }

        private void bind(SchedulerSection section) {
            if (section == null) return;
            mHeaderBinding.setSection(section);
            mHeaderBinding.executePendingBindings();
        }
    }
}
