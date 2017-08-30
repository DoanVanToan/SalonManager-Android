package com.framgia.fsalon.screen.customerinfo.user;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.databinding.FragmentUserBinding;
import com.framgia.fsalon.utils.Constant;

import static com.framgia.fsalon.utils.Constant.RequestPermission.REQUEST_CALL_PERMISSION;

/**
 * User Screen.
 */
public class UserFragment extends Fragment {
    private UserContract.ViewModel mViewModel;
    private static final int RESULT = 2;
    private static final String USER = "USER";
    private User mUser;

    public static UserFragment newInstance(User user) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constant.BUNDLE_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = getArguments().getParcelable(Constant.BUNDLE_USER);
        mViewModel = new UserViewModel(this, mUser);
        UserContract.Presenter presenter =
            new UserPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentUserBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user,
            container, false);
        binding.setViewModel((UserViewModel) mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    public void onStop() {
        mViewModel.onStop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PERMISSION:
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT) {
                User user = data.getExtras().getParcelable(USER);
                mUser.setBirthday(user.getBirthday());
                mUser.setName(user.getName());
                mUser.setPhone(user.getPhone());
                mUser.setAvatar(user.getAvatar());
            }
        }
    }
}
