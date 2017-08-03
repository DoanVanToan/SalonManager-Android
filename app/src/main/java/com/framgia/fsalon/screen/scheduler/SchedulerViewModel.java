package com.framgia.fsalon.screen.scheduler;

import android.app.FragmentManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.IntDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.ManageBookingResponse;
import com.framgia.fsalon.utils.Utils;
import com.framgia.fsalon.utils.navigator.Navigator;
import com.framgia.fsalon.wiget.TopSheetBehavior;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.framgia.fsalon.data.source.remote.ManageBookingRemoteDataSource.FILTER_DAY;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_CALENDAR;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_TODAY;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_TOMORROW;
import static com.framgia.fsalon.screen.scheduler.SchedulerViewModel.TabFilter.TAB_YESTERDAY;
import static com.framgia.fsalon.utils.Constant.ApiParram.FIRST_PAGE;
import static com.framgia.fsalon.utils.Constant.ApiParram.OUT_OF_INDEX;

/**
 * Exposes the data to be used in the Scheduler screen.
 */
public class SchedulerViewModel extends BaseObservable
    implements SchedulerContract.ViewModel, DatePickerDialog.OnDateSetListener {
    private SchedulerContract.Presenter mPresenter;
    private int mTabFilter;
    private SchedulerAdapter mAdapter;
    private Navigator mNavigator;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean mIsLoadingMore = false;
    private Calendar mCalendar;
    private FragmentManager mFragmentManager;
    private RecyclerView.OnScrollListener mScrollListenner = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy <= 0) {
                return;
            }
            LinearLayoutManager layoutManager =
                (LinearLayoutManager) recyclerView.getLayoutManager();
            if (layoutManager == null) {
                return;
            }
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
            if (!mIsLoadingMore && (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                mIsLoadingMore = true;
                mPresenter.loadMoreData();
            }
        }
    };

    public SchedulerViewModel(SchedulerFragment fragment, FragmentManager fragmentManager) {
        setTabFilter(TAB_TODAY);
        mNavigator = new Navigator(fragment);
        mFragmentManager = fragmentManager;
        setLayoutManager(new StickyHeaderLayoutManager());
        setAdapter(new SchedulerAdapter(new ArrayList<ManageBookingResponse>()));
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
    public void onItemFilterClick(@TabFilter int tab, View topSheet) {
        setTabFilter(tab);
        switch (tab) {
            case TAB_CALENDAR:
                filterRandomCalendar();
                break;
            case TAB_TODAY:
                mPresenter
                    .getSchedulers(FILTER_DAY, FIRST_PAGE, OUT_OF_INDEX,
                        OUT_OF_INDEX, Utils.createTimeStamp(tab), OUT_OF_INDEX);
                break;
            case TAB_TOMORROW:
                mPresenter
                    .getSchedulers(FILTER_DAY, FIRST_PAGE, OUT_OF_INDEX,
                        OUT_OF_INDEX, Utils.createTimeStamp(tab), OUT_OF_INDEX);
                break;
            case TAB_YESTERDAY:
                mPresenter
                    .getSchedulers(FILTER_DAY, FIRST_PAGE, OUT_OF_INDEX,
                        OUT_OF_INDEX, Utils.createTimeStamp(tab), OUT_OF_INDEX);
                break;
            default:
                break;
        }
        TopSheetBehavior.from(topSheet).setState(TopSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void onSchedulerSuccessful(List<ManageBookingResponse> sections) {
        mAdapter.updateData(sections);
    }

    @Override
    public void onSchedulerFail() {
        mNavigator.showToast(R.string.title_scheduler_failure);
    }

    @Override
    public void showLoadMore() {
        setLoadingMore(true);
    }

    @Override
    public void hideLoadMore() {
        setLoadingMore(false);
    }

    @Override
    public void filterRandomCalendar() {
        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }
        DatePickerDialog datePicker =
            DatePickerDialog.newInstance(this, mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        datePicker.show(mFragmentManager, "");
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

    @Bindable
    public boolean isLoadingMore() {
        return mIsLoadingMore;
    }

    public void setLoadingMore(boolean loadingMore) {
        mIsLoadingMore = loadingMore;
        notifyPropertyChanged(BR.loadingMore);
    }

    @Bindable
    public RecyclerView.OnScrollListener getScrollListenner() {
        return mScrollListenner;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        int time = (int) (cal.getTimeInMillis() / 1000);
        mPresenter
            .getSchedulers(FILTER_DAY, FIRST_PAGE, OUT_OF_INDEX, OUT_OF_INDEX, time, OUT_OF_INDEX);
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
