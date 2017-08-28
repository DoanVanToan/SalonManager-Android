package com.framgia.fsalon.screen.imagedetail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.ImageResponse;
import com.framgia.fsalon.databinding.ActivityImageDetailBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Imagedetail Screen.
 */
public class ImageDetailActivity extends AppCompatActivity {
    private ImageDetailContract.ViewModel mViewModel;
    private static final String BUNDLE_LIST_IMAGE = "BUNDLE_LIST_IMAGE";

    public static Intent getInstance(Context context, List<ImageResponse> images) {
        Intent intent = new Intent(context, ImageDetailActivity.class);
        Bundle args = new Bundle();
        args.putParcelableArrayList(BUNDLE_LIST_IMAGE, (ArrayList<? extends Parcelable>) images);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<ImageResponse> images = null;
        if (getIntent().getExtras() != null) {
            images = getIntent().getExtras().getParcelableArrayList(BUNDLE_LIST_IMAGE);
        }
        mViewModel = new ImageDetailViewModel(images);
        ImageDetailContract.Presenter presenter =
            new ImageDetailPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityImageDetailBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_image_detail);
        binding.setViewModel((ImageDetailViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
