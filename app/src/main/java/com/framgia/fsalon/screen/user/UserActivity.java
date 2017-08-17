package com.framgia.fsalon.screen.user;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.data.source.BookingRepository;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import com.framgia.fsalon.data.source.remote.BookingRemoteDataSource;
import com.framgia.fsalon.databinding.ActivityUserBinding;
import com.framgia.fsalon.utils.Constant;

/**
 * User Screen.
 */
public class UserActivity extends AppCompatActivity {
    private UserContract.ViewModel mViewModel;

    public static Intent getInstance(Context context, User user) {
        Intent intent = new Intent(context, UserActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(Constant.BUNDLE_USER, user);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = getIntent().getExtras().getParcelable(Constant.BUNDLE_USER);
        mViewModel = new UserViewModel(this, user);
        UserContract.Presenter presenter =
            new UserPresenter(mViewModel, user.getPhone(),
                new BookingRepository(
                    new BookingRemoteDataSource(FSalonServiceClient.getInstance())));
        mViewModel.setPresenter(presenter);
        ActivityUserBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_user);
        binding.setViewModel((UserViewModel) mViewModel);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.REQUEST_CALL_PERMISSION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    mViewModel.onPermissionDenied();
                } else {
                    mViewModel.onPermissionGranted();
                }
                break;
            default:
                break;
        }
    }
}
