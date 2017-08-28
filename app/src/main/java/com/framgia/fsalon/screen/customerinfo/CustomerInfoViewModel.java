package com.framgia.fsalon.screen.customerinfo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.screen.billcustomer.BillCustomerFragment;
import com.framgia.fsalon.screen.customerinfo.user.UserFragment;
import com.framgia.fsalon.screen.imagecustomer.ImageCustomerFragment;

import static com.framgia.fsalon.screen.customerinfo.CustomerInfoViewModel.Tab.BILL;
import static com.framgia.fsalon.screen.customerinfo.CustomerInfoViewModel.Tab.INFO;
import static com.framgia.fsalon.screen.customerinfo.CustomerInfoViewModel.Tab.PICTURE;

/**
 * Exposes the data to be used in the CustomerInfo screen.
 */
public class CustomerInfoViewModel extends BaseObservable
    implements CustomerInfoContract.ViewModel {
    private CustomerInfoContract.Presenter mPresenter;
    private CustomerInfoAdapter mPagerAdapter;
    int[] mImageResource = {R.drawable.ic_user_trans, R.drawable.ic_list, R.drawable.ic_picture};

    public CustomerInfoViewModel(AppCompatActivity activity, User user) {
        mPagerAdapter = new CustomerInfoAdapter(activity.getSupportFragmentManager());
        mPagerAdapter.addFragment(UserFragment.newInstance(user), INFO);
        mPagerAdapter.addFragment(BillCustomerFragment.newInstance(user.getId()), BILL);
        mPagerAdapter.addFragment(ImageCustomerFragment.newInstance(user.getId()), PICTURE);
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

    @Bindable
    public CustomerInfoAdapter getPagerAdapter() {
        return mPagerAdapter;
    }

    public void setPagerAdapter(CustomerInfoAdapter pagerAdapter) {
        mPagerAdapter = pagerAdapter;
        notifyPropertyChanged(BR.pagerAdapter);
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

    public int[] getImageResource() {
        return mImageResource;
    }

    public void setImageResource(int[] imageResource) {
        mImageResource = imageResource;
    }
}
