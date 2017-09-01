package com.framgia.fsalon.utils.permission;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;

import static com.framgia.fsalon.utils.Constant.RequestPermission.REQUEST_PERMISSION_CAMERA;
import static com.framgia.fsalon.utils.Constant.RequestPermission.REQUEST_PERMISSION_WRITE_STORGE;

/**
 * Created by beepi on 29/08/2017.
 */
public class PermissionUtils {
    public static boolean checkCameraPermission(final AppCompatActivity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(activity).setMessage(R.string.msg_request_read_camera)
                    .setPositiveButton(R.string.action_agree,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUEST_PERMISSION_CAMERA);
                            }
                        })
                    .setNegativeButton(R.string.action_dis_agree, null)
                    .show();
            } else {
                ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
            }
            return false;
        }
        return true;
    }

    public static boolean checkWritePermission(final AppCompatActivity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(activity).setMessage(R.string.msg_request_write_file)
                    .setPositiveButton(R.string.action_agree,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(activity, new String[]{
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                                }, REQUEST_PERMISSION_WRITE_STORGE);
                            }
                        })
                    .setNegativeButton(R.string.action_dis_agree, null)
                    .show();
            } else {
                ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSION_WRITE_STORGE);
            }
            return false;
        }
        return true;
    }
}
