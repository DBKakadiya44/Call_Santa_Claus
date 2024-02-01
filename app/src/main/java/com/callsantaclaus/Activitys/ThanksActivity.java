package com.callsantaclaus.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.callsantaclaus.ADS.AdsManager;
import com.callsantaclaus.ADS.InterstitialAD;
import com.callsantaclaus.ADS.Native;
import com.callsantaclaus.R;
import com.callsantaclaus.databinding.ActivityThanksBinding;

public class ThanksActivity extends AppCompatActivity {
    ActivityThanksBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThanksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        Native aNative = new Native(this);
        aNative.ShowNative(this, findViewById(R.id.native_container),3);

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        binding.thanksbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                    @Override
                    public void onAdLoadFailed() {
                        startActivity(new Intent(ThanksActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onInterstitialDismissed() {
                        startActivity(new Intent(ThanksActivity.this, MainActivity.class));
                    }
                });
            }
        });

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