package com.framgia.fsalon.screen.customerinfo;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.screen.info.InfoUserFragment;

import static com.framgia.fsalon.screen.customerinfo.CustomerInfoViewModel.Tab.BILL;
import static com.framgia.fsalon.screen.customerinfo.CustomerInfoViewModel.Tab.INFO;
import static com.framgia.fsalon.screen.customerinfo.CustomerInfoViewModel.Tab.PICTURE;

/**
 * Exposes the data to be used in the CustomerInfo screen.
 */
public class CustomerInfoViewModel implements CustomerInfoContract.ViewModel {
    private CustomerInfoContract.Presenter mPresenter;
    private CustomerInfoAdapter mPagerAdapter;

    public CustomerInfoViewModel(AppCompatActivity activity) {
        mPagerAdapter = new CustomerInfoAdapter(activity.getSupportFragmentManager());
        mPagerAdapter.addFragment(InfoUserFragment.newInstance(), INFO);
        mPagerAdapter.addFragment(InfoUserFragment.newInstance(), BILL);
        mPagerAdapter.addFragment(InfoUserFragment.newInstance(), PICTURE);
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
    public void setPresenter(CustomerInfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public CustomerInfoAdapter getPagerAdapter() {
        return mPagerAdapter;
    }

    public void setPagerAdapter(CustomerInfoAdapter pagerAdapter) {
        mPagerAdapter = pagerAdapter;
    }

    /**
     * Title of pages
     */
    @StringDef({INFO, BILL, PICTURE})
    public @interface Tab {
        String INFO = "Info";
        String BILL = "Bill";
        String PICTURE = "Picture";
    }
}
