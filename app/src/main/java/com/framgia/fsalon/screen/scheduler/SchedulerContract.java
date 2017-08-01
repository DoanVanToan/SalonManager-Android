package com.framgia.fsalon.screen.scheduler;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.SchedulerSection;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface SchedulerContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onItemFilterClick(@SchedulerViewModel.TabFilter int tab);
        void onSchedulerSuccessful(List<SchedulerSection> sections);
        void onSchedulerFail();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getSchedulers();
    }
}
