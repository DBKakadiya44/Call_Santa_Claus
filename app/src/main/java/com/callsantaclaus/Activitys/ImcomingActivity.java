package com.callsantaclaus.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.callsantaclaus.ADS.AdsManager;
import com.callsantaclaus.ADS.InterstitialAD;
import com.callsantaclaus.R;
import com.callsantaclaus.databinding.ActivityImcomingBinding;

public class ImcomingActivity extends AppCompatActivity {
ActivityImcomingBinding binding;
    int MAKECALL = 100;
    String[] PERMISSIONS_START_CALL = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.READ_PHONE_STATE"};
    InterstitialAD helper;
    AdsManager adsManager = null;
    private static final int CAMERA_AUDIO_PERMISSION_REQUEST_CODE = 1;
    private boolean cameraPermissionGranted = false;
    private boolean audioPermissionGranted = false;
    private boolean readphonestateGranted = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImcomingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        binding.rejectcall.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.ivback.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.acceptcall.setOnClickListener(view -> {

            cameraPermissionGranted = checkPermission(Manifest.permission.CAMERA);
            audioPermissionGranted = checkPermission(Manifest.permission.RECORD_AUDIO);
            readphonestateGranted = checkPermission(Manifest.permission.READ_PHONE_STATE);

            if (!cameraPermissionGranted || !audioPermissionGranted || !readphonestateGranted) {
                requestPermissions();
            } else {
                startNextActivity();
            }

        });

    }

    private boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_PHONE_STATE};
        ActivityCompat.requestPermissions(this, permissions, CAMERA_AUDIO_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_AUDIO_PERMISSION_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    if (permissions[i].equals(Manifest.permission.CAMERA)) {
                        cameraPermissionGranted = true;
                    } else if (permissions[i].equals(Manifest.permission.RECORD_AUDIO)) {
                        audioPermissionGranted = true;
                    }
                }
            }

            if (cameraPermissionGranted && audioPermissionGranted) {
                // Both permissions are granted, proceed to the next activity
                startNextActivity();
            } else {
                // Permissions not granted, inform the user
                Toast.makeText(this, "Both camera and audio permissions are required.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startNextActivity() {
        // Start the next activity
        SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("cameraPermission", true);
        editor.putBoolean("audioPermission", true);
        editor.apply();

        // Start the next activity

        int pos = getIntent().getIntExtra("cnt",1);

        if(pos==1){
            Intent intent = new Intent(ImcomingActivity.this, VideocallActivity.class);
            startActivity(intent);
            finish();
        }
        if(pos==2){
            Intent intent = new Intent(ImcomingActivity.this, AudiocallActivity.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
            @Override
            public void onAdLoadFailed() {
                finish();
            }

            @Override
            public void onInterstitialDismissed() {
                finish();
            }
        });
    }

}