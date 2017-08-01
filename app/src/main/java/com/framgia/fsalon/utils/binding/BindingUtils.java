package com.framgia.fsalon.utils.binding;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.graphics.drawable.Drawable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.Stylist;
import com.framgia.fsalon.screen.booking.BookingViewModel;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

/**
 * Created by MyPC on 20/07/2017.
 */
public class BindingUtils {
    @BindingAdapter("errorText")
    public static void setErrorText(TextInputLayout layout, String text) {
        layout.setError(text);
    }

    @BindingAdapter("android:src")
    public static void setImage(ImageView view, int src) {
        view.setImageResource(src);
    }

    @BindingAdapter(value = {"recyclerAdapter", "layoutManager"}, requireAll = false)
    public static void setAdapterForRecyclerView(RecyclerView recyclerView,
                                                 RecyclerView.Adapter adapter,
                                                 RecyclerView.LayoutManager layoutManager) {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DeviderItemDecoration(1));
    }

    @BindingAdapter({"searchableSpinnerAdapter"})
    public static void setSearchableSpinnerAdapter(SearchableSpinner view, ArrayAdapter adapter) {
        view.setAdapter(adapter);
        view.setTitle(view.getContext().getResources().getString(R.string.title_stylist));
        view.setPositiveButton(view.getContext().getResources().getString(R.string.action_close));
    }

    @BindingAdapter({"resourceId"})
    public static void setResourceId(LinearLayout layout, int resId) {
        layout.setBackgroundResource(resId);
    }

    @InverseBindingAdapter(attribute = "stylistId", event = "stylistIdAttrChanged")
    public static int captureStylistId(SearchableSpinner view) {
        Object selectedItem = view.getSelectedItem();
        return ((Stylist) selectedItem).getId();
    }

    @BindingAdapter(value = {"viewModel", "stylistId", "stylistIdAttrChanged"}, requireAll = false)
    public static void setCategoryId(SearchableSpinner view, final BookingViewModel viewModel,
                                     int value, final InverseBindingListener bindingListener) {
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bindingListener.onChange();
                viewModel.getData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        view.setOnItemSelectedListener(listener);
    }

    @BindingAdapter("bind:viewPagerAdapter")
    public static void setViewPagerAdapter(ViewPager viewPager, PagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);
    }

    @BindingAdapter("bind:listener")
    public static void setBottomNavigationViewListener(BottomNavigationView view,
                                                       BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter("bind:tabSelect")
    public static void setSelectedTab(ViewPager viewPager, int position) {
        viewPager.setCurrentItem(position);
    }

    @BindingAdapter({"colorId"})
    public static void setcolorId(TextView view, int colorId) {
        view.setTextColor(view.getResources().getColor(colorId));
    }

    @BindingAdapter(value = {"bind:imageUrl", "bind:error"}, requireAll = false)
    public static void loadImage(ImageView view, String imageUrl, Drawable error) {
        if (error == null) {
            Glide.with(view.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_no_image)
                .into(view);
        } else {
            Glide.with(view.getContext()).load(imageUrl).centerCrop().placeholder(error).into
                (view);
        }
    }
}
