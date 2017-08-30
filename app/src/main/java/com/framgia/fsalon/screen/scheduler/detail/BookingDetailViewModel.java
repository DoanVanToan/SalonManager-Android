package com.framgia.fsalon.screen.scheduler.detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.framgia.fsalon.BR;
import com.framgia.fsalon.FSalonApplication;
import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.BookingOder;
import com.framgia.fsalon.data.model.ImageResponse;
import com.framgia.fsalon.data.model.Status;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.screen.editbooking.EditBookingActivity;
import com.framgia.fsalon.screen.editstatusdialog.EditStatusDialogFragment;
import com.framgia.fsalon.utils.Constant;
import com.framgia.fsalon.utils.ImagePicker;
import com.framgia.fsalon.utils.Utils;
import com.framgia.fsalon.utils.navigator.Navigator;
import com.framgia.fsalon.utils.permission.PermissionUtils;
import com.github.clans.fab.FloatingActionMenu;

import java.io.File;

import static android.app.Activity.RESULT_OK;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.framgia.fsalon.data.model.BookingOder.STATUS_IN_PROGRESS;
import static com.framgia.fsalon.screen.scheduler.detail.BookingDetailViewModel.SideCapture.SIDE_BEHIND;
import static com.framgia.fsalon.screen.scheduler.detail.BookingDetailViewModel.SideCapture.SIDE_FRONT_UP;
import static com.framgia.fsalon.screen.scheduler.detail.BookingDetailViewModel.SideCapture.SIDE_LEFT;
import static com.framgia.fsalon.screen.scheduler.detail.BookingDetailViewModel.SideCapture.SIDE_RIGHT;
import static com.framgia.fsalon.utils.Constant.Permission.PERMISSION_MAIN_WORKER;
import static com.framgia.fsalon.utils.Constant.RequestPermission.REQUEST_ADMIN_BOOKING_ACTIVITY;
import static com.framgia.fsalon.utils.Constant.RequestPermission.REQUEST_CALL_PERMISSION;
import static com.framgia.fsalon.utils.Constant.RequestPermission.REQUEST_PERMISSION_CAMERA;
import static com.framgia.fsalon.utils.Constant.RequestPermission.REQUEST_PICK_IMAGE;
import static com.framgia.fsalon.utils.ImagePicker.BUNDLE_IS_CAPTURED;
import static com.framgia.fsalon.utils.ImagePicker.TEMP_IMAGE_NAME;

/**
 * Exposes the data to be used in the Detail screen.
 */
public class BookingDetailViewModel extends BaseObservable
    implements BookingDetailContract.ViewModel {
    private static final String EDIT_STATUS_DIALOG = "EDIT_STATUS_DIALOG";
    private BookingDetailContract.Presenter mPresenter;
    private BookingOder mBookingOder;
    private int mId;
    private int mProgressBarVisibility;
    private boolean mIsFinish = true;
    private Navigator mNavigator;
    private boolean mIsSelected;
    private boolean mIsChangeStatus;
    private AppCompatActivity mActivity;
    private int mSidePhoto;
    private String mPathImageSideLeft;
    private String mPathImageSideRight;
    private String mPathImageSideBehind;
    private String mPathImageSideFrontUp;
    private User mCurrentUser;
    private int mAddPhotoVisibility = GONE;
    private SwipeRefreshLayout.OnRefreshListener mListener =
        new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getBookingById(mId);
            }
        };

    public BookingDetailViewModel(AppCompatActivity activity, int id) {
        mActivity = activity;
        mNavigator = new Navigator(activity);
        mId = id;
        mActivity = activity;
    }

    public SwipeRefreshLayout.OnRefreshListener getListener() {
        return mListener;
    }

    public void setListener(SwipeRefreshLayout.OnRefreshListener listener) {
        mListener = listener;
    }

    @Bindable
    public BookingOder getBookingOder() {
        return mBookingOder;
    }

    public void setBookingOder(BookingOder bookingOder) {
        mBookingOder = bookingOder;
        notifyPropertyChanged(BR.bookingOder);
    }

    @Bindable
    public int getProgressBarVisibility() {
        return mProgressBarVisibility;
    }

    public void setProgressBarVisibility(int progressBarVisibility) {
        mProgressBarVisibility = progressBarVisibility;
        notifyPropertyChanged(BR.progressBarVisibility);
    }

    @Bindable
    public boolean isFinish() {
        return mIsFinish;
    }

    public void setFinish(boolean finish) {
        mIsFinish = finish;
        notifyPropertyChanged(BR.finish);
    }

    @Bindable
    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
        notifyPropertyChanged(BR.selected);
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
    public void setPresenter(BookingDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetBookingError(String msg) {
        mNavigator.showToast(msg);
    }

    @Override
    public void onGetBookingSuccess(BookingOder bookingOder) {
        setBookingOder(bookingOder);
        setChangeStatus(Status.getStatuses(bookingOder.getStatus()).size() > 0);
        if (mCurrentUser.getPermission() == PERMISSION_MAIN_WORKER
            && bookingOder.getStatus() == STATUS_IN_PROGRESS
            && mCurrentUser.getId() == bookingOder.getStylistId()) {
            setAddPhotoVisibility(VISIBLE);
        }
    }

    @Override
    public void finishRefresh() {
        setFinish(true);
    }

    @Override
    public void showProgressBar() {
        setProgressBarVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        setProgressBarVisibility(GONE);
    }

    @Override
    public void callCustomer(View button) {
        FloatingActionMenu fapMenu = (FloatingActionMenu) button.getParent();
        fapMenu.close(true);
        if (ActivityCompat.checkSelfPermission(mNavigator.getContext(),
            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Utils.requestCallPermission(mNavigator.getContext());
        } else {
            onPermissionGranted();
        }
    }

    @Override
    public void editBooking(View button) {
        FloatingActionMenu fapMenu = (FloatingActionMenu) button.getParent();
        fapMenu.close(true);
        mNavigator.startActivityForResult(
            EditBookingActivity.getInstance(mNavigator.getContext(), mBookingOder),
            REQUEST_ADMIN_BOOKING_ACTIVITY);
    }

    @Override
    public void messageCustomer(View button) {
        FloatingActionMenu fapMenu = (FloatingActionMenu) button.getParent();
        fapMenu.close(true);
        // TODO: 8/8/2017
    }

    @Override
    public void onPermissionGranted() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mBookingOder.getPhone()));
        mNavigator.startActivity(intent);
    }

    @Override
    public void onPermissionDenied() {
        mNavigator.showToast(R.string.msg_no_permission);
    }

    public void returnData(BookingOder order) {
        mNavigator.showToast(R.string.msg_edit_success);
        setBookingOder(order);
        setId(order.getId());
        setSelected(false);
    }

    @Override
    public void getBooking() {
        showProgressBar();
        mPresenter.getBookingById(mId);
    }

    @Override
    public void onChangeStatusClick(View view) {
        FloatingActionMenu fapMenu = (FloatingActionMenu) view.getParent();
        fapMenu.close(true);
        if (mBookingOder == null) {
            return;
        }
        FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
        EditStatusDialogFragment newFragment = EditStatusDialogFragment.newInstance(mBookingOder
            .getId(), mBookingOder.getStatus());
        newFragment.show(ft, EDIT_STATUS_DIALOG);
    }

    @Override
    public void onGetData() {
        mPresenter.getBookingById(mId);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PERMISSION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    onPermissionDenied();
                } else {
                    onPermissionGranted();
                }
                break;
            case REQUEST_PERMISSION_CAMERA:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    onPermissionDenied();
                } else {
                    mNavigator.startActivityForResult(ImagePicker.getPickImageIntent(
                        FSalonApplication.getInstant()),
                        REQUEST_PICK_IMAGE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_ADMIN_BOOKING_ACTIVITY:
                returnData((BookingOder) data.getParcelableExtra(Constant.BUNDLE_ORDER));
                break;
            case REQUEST_PICK_IMAGE:
                if (data == null) {
                    return;
                }
                Uri uri = getPickImageResultUri(data);
                if (uri == null) {
                    return;
                }
                ImageResponse image = new ImageResponse();
                image.setPathOrigin(uri.toString());
                mPresenter.postImageByStylist(mId, image);
                updateImage(uri.toString());
                break;
            default:
                break;
        }
    }

    public void pickImage(@SideCapture int sideCapture) {
        if (PermissionUtils.checkCameraPermission(mActivity)) {
            setSidePhoto(sideCapture);
            mActivity.startActivityForResult(
                ImagePicker.getPickImageIntent(FSalonApplication.getInstant()),
                REQUEST_PICK_IMAGE);
        }
    }

    @Override
    public void updateImage(String pathOrigin) {
        switch (mSidePhoto) {
            case SIDE_LEFT:
                setPathImageSideLeft(pathOrigin);
                break;
            case SIDE_RIGHT:
                setPathImageSideRight(pathOrigin);
                break;
            case SIDE_BEHIND:
                setPathImageSideBehind(pathOrigin);
                break;
            case SIDE_FRONT_UP:
                setPathImageSideFrontUp(pathOrigin);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAddPhoto(LinearLayout layoutPhoto, View view) {
        FloatingActionMenu fapMenu = (FloatingActionMenu) view.getParent();
        fapMenu.close(true);
        layoutPhoto.setVisibility(VISIBLE);
    }

    @Override
    public void onDeterminePermissionSuccessfully(User user) {
        setCurrentUser(user);
    }

    @Bindable
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public boolean isChangeStatus() {
        return mIsChangeStatus;
    }

    public void setChangeStatus(boolean changeStatus) {
        mIsChangeStatus = changeStatus;
        notifyPropertyChanged(BR.changeStatus);
    }

    public AppCompatActivity getActivity() {
        return mActivity;
    }

    public void setActivity(AppCompatActivity activity) {
        mActivity = activity;
        notifyPropertyChanged(BR.activity);
    }

    @Bindable
    public String getPathImageSideLeft() {
        return mPathImageSideLeft;
    }

    public void setPathImageSideLeft(String pathImageSideLeft) {
        mPathImageSideLeft = pathImageSideLeft;
        notifyPropertyChanged(BR.pathImageSideLeft);
    }

    @Bindable
    public String getPathImageSideRight() {
        return mPathImageSideRight;
    }

    public void setPathImageSideRight(String pathImageSideRight) {
        mPathImageSideRight = pathImageSideRight;
        notifyPropertyChanged(BR.pathImageSideRight);
    }

    @Bindable
    public String getPathImageSideBehind() {
        return mPathImageSideBehind;
    }

    public void setPathImageSideBehind(String pathImageSideBehind) {
        mPathImageSideBehind = pathImageSideBehind;
        notifyPropertyChanged(BR.pathImageSideBehind);
    }

    @Bindable
    public String getPathImageSideFrontUp() {
        return mPathImageSideFrontUp;
    }

    public void setPathImageSideFrontUp(String pathImageSideFrontUp) {
        mPathImageSideFrontUp = pathImageSideFrontUp;
        notifyPropertyChanged(BR.pathImageSideFrontUp);
    }

    public int getSidePhoto() {
        return mSidePhoto;
    }

    public void setSidePhoto(int sidePhoto) {
        mSidePhoto = sidePhoto;
    }

    @Bindable
    public User getCurrentUser() {
        return mCurrentUser;
    }

    public void setCurrentUser(User currentUser) {
        mCurrentUser = currentUser;
        notifyPropertyChanged(BR.currentUser);
    }

    @Bindable
    public int getAddPhotoVisibility() {
        return mAddPhotoVisibility;
    }

    public void setAddPhotoVisibility(int addPhotoVisibility) {
        mAddPhotoVisibility = addPhotoVisibility;
        notifyPropertyChanged(BR.addPhotoVisibility);
    }

    private Uri getPickImageResultUri(Intent data) {
        boolean isCamera = false;
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                isCamera = bundle.getBoolean(BUNDLE_IS_CAPTURED);
            }
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    /**
     * Get URI to image received from capture by camera.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File imageFile = mActivity.getExternalCacheDir();
        if (imageFile != null) {
            outputFileUri = Uri.fromFile(new File(imageFile.getPath(), TEMP_IMAGE_NAME));
        }
        return outputFileUri;
    }

    /**
     * Define all side of customer 's photos
     */
    @IntDef({SIDE_LEFT, SIDE_RIGHT, SIDE_BEHIND, SIDE_FRONT_UP})
    public @interface SideCapture {
        int SIDE_LEFT = 0;
        int SIDE_RIGHT = 1;
        int SIDE_BEHIND = 2;
        int SIDE_FRONT_UP = 3;
    }
}
