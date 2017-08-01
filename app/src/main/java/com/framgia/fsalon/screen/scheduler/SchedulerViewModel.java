package com.framgia.fsalon.screen.scheduler;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.SchedulerSection;
import com.framgia.fsalon.utils.navigator.Navigator;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_CALENDAR;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_TODAY;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_TOMORROW;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_YESTERDAY;

/**
 * Exposes the data to be used in the Scheduler screen.
 */
public class SchedulerViewModel extends BaseObservable implements SchedulerContract.ViewModel {
    private SchedulerContract.Presenter mPresenter;
    private int mTabFilter;
    private SchedulerAdapter mAdapter;
    private Navigator mNavigator;
    private RecyclerView.LayoutManager mLayoutManager;

    public SchedulerViewModel(SchedulerFragment fragment) {
        setTabFilter(TAB_TODAY);
        mNavigator = new Navigator(fragment);
        mLayoutManager = new StickyHeaderLayoutManager();
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(SchedulerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public int getTabFilter() {
        return mTabFilter;
    }

    public void setTabFilter(@TabFilter int tabFilter) {
        mTabFilter = tabFilter;
        notifyPropertyChanged(BR.tabFilter);
    }

    @Override
    public void onItemFilterClick(@TabFilter int tab) {
        setTabFilter(tab);
    }

    @Override
    public void onSchedulerSuccessful(List<SchedulerSection> sections) {
        setAdapter(new SchedulerAdapter(sections));
    }

    @Override
    public void onSchedulerFail() {
        mNavigator.showToast(R.string.title_scheduler_failure);
    }

    @Bindable
    public SchedulerAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(SchedulerAdapter adapter) {
        mAdapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }

    @IntDef({TAB_TODAY, TAB_YESTERDAY, TAB_TOMORROW, TAB_CALENDAR})
    @Target(ElementType.PARAMETER)
    public @interface TabFilter {
        int TAB_TODAY = 0;
        int TAB_YESTERDAY = 1;
        int TAB_TOMORROW = 2;
        int TAB_CALENDAR = 3;
    }
}
