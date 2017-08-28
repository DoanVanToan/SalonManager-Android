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
    private static final String BUNDLE_POSITION_IMAGE = "BUNDLE_POSITION_IMAGE";

    public static Intent getInstance(Context context, List<ImageResponse> images, int position) {
        Intent intent = new Intent(context, ImageDetailActivity.class);
        Bundle args = new Bundle();
        args.putParcelableArrayList(BUNDLE_LIST_IMAGE, (ArrayList<? extends Parcelable>) images);
        args.putInt(BUNDLE_POSITION_IMAGE, position);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<ImageResponse> images = null;
        int position = 0;
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            images = bundle.getParcelableArrayList(BUNDLE_LIST_IMAGE);
            position = bundle.getInt(BUNDLE_POSITION_IMAGE);
        }
        mViewModel = new ImageDetailViewModel(images, position);
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
