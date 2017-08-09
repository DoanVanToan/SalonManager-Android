package com.framgia.fsalon.screen.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.source.UserDataSource;
import com.framgia.fsalon.data.source.UserRepository;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import com.framgia.fsalon.data.source.local.UserLocalDataSource;
import com.framgia.fsalon.data.source.local.sharepref.SharePreferenceImp;
import com.framgia.fsalon.data.source.remote.UserRemoteDataSource;
import com.framgia.fsalon.databinding.FragmentInfoUserBinding;

/**
 * InfoUser Screen.
 */
public class InfoUserFragment extends Fragment {
    private InfoUserContract.ViewModel mViewModel;

    public static InfoUserFragment newInstance() {
        return new InfoUserFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new InfoUserViewModel(this);
        UserDataSource.RemoteDataSource remoteDataSource =
            new UserRemoteDataSource(FSalonServiceClient.getInstance());
        UserDataSource.LocalDataSource localDataSource =
            new UserLocalDataSource(new SharePreferenceImp(getContext()));
        InfoUserContract.Presenter presenter =
            new InfoUserPresenter(mViewModel,
                new UserRepository(remoteDataSource, localDataSource));
        mViewModel.setPresenter(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentInfoUserBinding binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_info_user, container, false);
        binding.setViewModel((InfoUserViewModel) mViewModel);
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
}
