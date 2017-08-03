package com.framgia.fsalon.screen.scheduler;

import android.view.View;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.ManageBookingResponse;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SchedulerContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onItemFilterClick(@SchedulerViewModel.TabFilter int tab, View topSheet);
        void onSchedulerSuccessful(List<ManageBookingResponse> sections);
        void onSchedulerFail();
        void showLoadMore();
        void hideLoadMore();
        void filterRandomCalendar();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getSchedulers(String filterChoice, int page, int perpage, int status, int startDate,
                           int endDate);
        void loadMoreData();
    }
}
