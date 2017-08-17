package com.framgia.fsalon.screen.customer;

import android.app.FragmentManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.utils.navigator.Navigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Exposes the data to be used in the Customer screen.
 */
public class CustomerViewModel extends BaseObservable implements CustomerContract.ViewModel {
    private CustomerContract.Presenter mPresenter;
    private CustomerAdapter mCustomerAdapter;
    private Navigator mNavigator;
    private FragmentManager mFragmentManager;
    private boolean mIsLoadingMore;
    private RecyclerView.OnScrollListener mListener = new RecyclerView.OnScrollListener() {
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

    public CustomerViewModel(CustomerFragment fragment, FragmentManager fragmentManager) {
        mNavigator = new Navigator(fragment);
        mFragmentManager = fragmentManager;
        setCustomerAdapter(new CustomerAdapter(mNavigator.getContext(), new
            ArrayList<User>(), CustomerViewModel.this));
    }

    @Bindable
    public boolean isLoadingMore() {
        return mIsLoadingMore;
    }

    public void setLoadingMore(boolean loadingMore) {
        mIsLoadingMore = loadingMore;
        notifyPropertyChanged(BR.loadingMore);
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
    public void setPresenter(CustomerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public CustomerAdapter getCustomerAdapter() {
        return mCustomerAdapter;
    }

    public void setCustomerAdapter(CustomerAdapter customerAdapter) {
        mCustomerAdapter = customerAdapter;
        notifyPropertyChanged(BR.customerAdapter);
    }

    @Bindable
    public RecyclerView.OnScrollListener getListener() {
        return mListener;
    }

    public void setListener(RecyclerView.OnScrollListener listener) {
        mListener = listener;
        notifyPropertyChanged(BR.listener);
    }

    @Override
    public void onCustomersSuccessful(List<User> sections) {
        mCustomerAdapter.onUpdatePage(sections);
    }

    @Override
    public void onCustomerFail() {
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

    public void onCustomerItemClick(User user) {
//        mNavigator.startActivity(UserActivity.getInstance(mNavigator.getContext(), user));
    }
}
