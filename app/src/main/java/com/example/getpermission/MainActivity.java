package com.example.getpermission;
//https://stackoverflow.com/questions/44708355/how-to-seek-permission-for-external-sd-card

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static int MY_PERMISSIONS_REQUEST_READ_WRITE_EXTERNAL_STORAGE = 11;
    protected static final int RESULT_SETTINGS = 1;

    private static final String LOG_TAG = "123";


    MP3_Module MP3 = new MP3_Module();

    boolean mReadWritePermissionGranted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        if (!mReadWritePermissionGranted)
        {
            askPermission();
        }


        MP3.MP3DateiLaden ();

    }

    /**************************************************************************
     *
     * ask for file access permission
     *
     *************************************************************************/
    private void askPermission()
    {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED)
        {
            Log.d(LOG_TAG, "permission immediately granted");
            //   setupPathList();
            mReadWritePermissionGranted = true;
        } else
        {
            Log.d(LOG_TAG, "request permission");


            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_WRITE_EXTERNAL_STORAGE);
        }
    }


    /**************************************************************************
     *
     * called when access request has been denied or granted
     *
     *************************************************************************/
    @Override
    public void onRequestPermissionsResult
    (
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    )
    {
        Log.d(LOG_TAG, "onRequestPermissionsResult()");

        //noinspection SwitchStatementWithTooFewBranches
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_READ_WRITE_EXTERNAL_STORAGE:
            {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED))
                {
                    Log.d(LOG_TAG, "onRequestPermissionsResult(): permission granted");
                    mReadWritePermissionGranted = true;
//                    setupPathList();
                } else {
                    Log.d(LOG_TAG, "onRequestPermissionsResult(): permission denied");
//                    Toast.makeText(getApplicationContext(), R.string.str_permission_denied, Toast.LENGTH_LONG).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }
            break;

            default:
                Log.d(LOG_TAG, "onRequestPermissionsResult(): unexpected request code " + requestCode);
                break;
        }
    }






}
