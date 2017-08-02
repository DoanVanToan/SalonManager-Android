package com.framgia.fsalon.screen.scheduler;

import android.view.View;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.ManageBookingResponse;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface SchedulerContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onItemFilterClick(@SchedulerViewModel.TabFilter int tab, View topSheet);
        void onSchedulerSuccessful(List<ManageBookingResponse> sections);
        void onSchedulerFail();
        void showLoadMore();
        void hideLoadMore();
        void onBookingItemClick(int id);
        void onClickTopSheet(View topSheet);
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
