package com.callsantaclaus.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.callsantaclaus.ADS.AdsManager;
import com.callsantaclaus.ADS.InterstitialAD;
import com.callsantaclaus.R;
import com.callsantaclaus.databinding.ActivityAudiocallBinding;

public class AudiocallActivity extends AppCompatActivity {
    ActivityAudiocallBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    int time;
    int min=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAudiocallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        binding.ivback.setOnClickListener(view -> {
            onBackPressed();
        });

        binding.endcall.setOnClickListener(view -> {
            onBackPressed();
        });

        settimer();

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

    private void settimer() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                time++;

                if(time==60){
                    min++;
                    time=0;
                }

                if(min==0){
                    if(time<=9){
                        binding.timer.setText("00:0"+time);
                    }else{
                        binding.timer.setText("00:"+time);
                    }
                }else{
                    if(min<=9){
                        if(time<=9){
                            binding.timer.setText("0"+min+":"+"0"+time);
                        }else{
                            binding.timer.setText("0"+min+":"+time);
                        }
                    }else{
                        if(time<=9){
                            binding.timer.setText(min+":"+"0"+time);
                        }else{
                            binding.timer.setText(min+":"+time);
                        }
                    }

                }

                settimer();
            }
        }, 1000);
    }
}