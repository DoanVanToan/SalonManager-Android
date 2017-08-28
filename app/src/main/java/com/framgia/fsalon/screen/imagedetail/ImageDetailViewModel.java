package com.framgia.fsalon.screen.imagedetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.framgia.fsalon.data.model.ImageResponse;

import java.util.List;

/**
 * Exposes the data to be used in the Imagedetail screen.
 */
public class ImageDetailViewModel extends BaseObservable implements ImageDetailContract.ViewModel {
    private ImageDetailContract.Presenter mPresenter;
    private int mPosition = 0;
    private List<ImageResponse> mImages;
    private String mImageURL;

    public ImageDetailViewModel(List<ImageResponse> images, int position) {
        if (images != null && images.size() > 0) {
            mImages = images;
            mPosition = position;
            setImageURL(mImages.get(mPosition).getPathOrigin());
        }
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
    public void setPresenter(ImageDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onPreviouslyClick() {
        if (mImages == null) {
            return;
        }
        if (mPosition > 0) {
            mPosition--;
            setImageURL(mImages.get(mPosition).getPathOrigin());
        }
    }

    @Override
    public void onNextClick() {
        if (mImages == null) {
            return;
        }
        if (mPosition < (mImages.size() - 1)) {
            mPosition++;
            setImageURL(mImages.get(mPosition).getPathOrigin());
        }
    }

    @Bindable
    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
        notifyPropertyChanged(BR.position);
    }

    @Bindable
    public List<ImageResponse> getImages() {
        return mImages;
    }

    public void setImages(List<ImageResponse> images) {
        mImages = images;
        notifyPropertyChanged(BR.images);
    }

    @Bindable
    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String imageURL) {
        mImageURL = imageURL;
        notifyPropertyChanged(BR.imageURL);
    }
}
