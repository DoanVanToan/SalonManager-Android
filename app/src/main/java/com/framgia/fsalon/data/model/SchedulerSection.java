package com.framgia.fsalon.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

/**
 * Created by beepi on 28/07/2017.
 */
public class SchedulerSection extends BaseObservable {
    private List<Scheduler> mSections;
    private String mTitle;

    @Bindable
    public List<Scheduler> getSections() {
        return mSections;
    }

    public void setSections(List<Scheduler> scheduler) {
        mSections = scheduler;
        notifyPropertyChanged(BR.section);
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
        notifyPropertyChanged(BR.title);
    }
}
