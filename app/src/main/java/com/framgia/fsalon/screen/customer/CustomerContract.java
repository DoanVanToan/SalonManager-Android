package com.framgia.fsalon.screen.customer;

import com.framgia.fsalon.BasePresenter;
import com.framgia.fsalon.BaseViewModel;
import com.framgia.fsalon.data.model.User;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface CustomerContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onCustomersSuccessful(List<User> sections);
        void onCustomerFail();
        void showLoadMore();
        void hideLoadMore();
        void onSearchSuccessful(List<User> sections);
        void onSearchFail();
        void onBack();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getCustomers(int page);
        void loadMoreData();
        void loadMoreSearch(String keyword);
        void searchUser(String keyword, int page);
    }
}
