package com.framgia.fsalon.screen.customerinfo.user;

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
        User user = getArguments().getParcelable(Constant.BUNDLE_USER);
        mViewModel = new UserViewModel(this, user);
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
}
