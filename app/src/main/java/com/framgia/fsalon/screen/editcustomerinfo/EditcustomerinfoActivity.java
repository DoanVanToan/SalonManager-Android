package com.framgia.fsalon.screen.editcustomerinfo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.model.User;
import com.framgia.fsalon.databinding.ActivityEditcustomerinfoBinding;
import com.framgia.fsalon.utils.Constant;

/**
 * Editcustomerinfo Screen.
 */
public class EditcustomerinfoActivity extends AppCompatActivity {
    private EditcustomerinfoContract.ViewModel mViewModel;
    private User mUser;

    public static Intent getInstance(Context context, User user) {
        Intent intent = new Intent(context, EditcustomerinfoActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(Constant.BUNDLE_USER, user);
        intent.putExtras(args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = getIntent().getExtras().getParcelable(Constant.BUNDLE_USER);
        mViewModel = new EditcustomerinfoViewModel(mUser, this);
        EditcustomerinfoContract.Presenter presenter =
            new EditcustomerinfoPresenter(mViewModel);
        mViewModel.setPresenter(presenter);
        ActivityEditcustomerinfoBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_editcustomerinfo);
        binding.setViewModel((EditcustomerinfoViewModel) mViewModel);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = this.getContentResolver().query(uri,
                filePathColumn, null, null, null);
            cursor.moveToFirst();
            mViewModel.onUpdateAvatarSuccess(uri.toString());
            cursor.close();
        }
    }
}
