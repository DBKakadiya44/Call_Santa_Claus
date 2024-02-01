package com.callsantaclaus.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.callsantaclaus.ADS.AdsManager;
import com.callsantaclaus.ADS.InterstitialAD;
import com.callsantaclaus.ADS.Native;
import com.callsantaclaus.Adapters.CustomDialogClass;
import com.callsantaclaus.R;
import com.callsantaclaus.databinding.ActivityScheduleBinding;

public class ScheduleActivity extends AppCompatActivity {
    ActivityScheduleBinding binding;
    int callnum = 1;
    public static int calltime;
    InterstitialAD helper;
    AdsManager adsManager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScheduleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        Native aNative = new Native(this);
        aNative.ShowNative(this, findViewById(R.id.native_container),3);

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        int position = getIntent().getIntExtra("cnt", 0);
        int pos = position+1;

        binding.apptitle.setText("Santa Claus "+pos);

        binding.schedule2.setOnClickListener(view -> {
            CustomDialogClass cdd = new CustomDialogClass(ScheduleActivity.this, binding.schedule);
            cdd.show();
        });

        binding.audiocall.setOnClickListener(view -> {

            binding.audiocall.setBackgroundResource(R.drawable.dark_dot);
            binding.videocall.setBackgroundResource(R.drawable.border_bg);
            callnum = 2;
        });
        binding.videocall.setOnClickListener(view -> {
            binding.videocall.setBackgroundResource(R.drawable.dark_dot);
            binding.audiocall.setBackgroundResource(R.drawable.border_bg);
            callnum = 1;
        });

        binding.callbtn.setOnClickListener(view -> {

            Toast.makeText(this, "The Call has been scheduled Sucessfully..", Toast.LENGTH_SHORT).show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                        @Override
                        public void onAdLoadFailed() {
                            Intent intent = new Intent(ScheduleActivity.this, ImcomingActivity.class);
                            intent.putExtra("cnt",callnum);
                            startActivity(intent);                        }

                        @Override
                        public void onInterstitialDismissed() {
                            Intent intent = new Intent(ScheduleActivity.this, ImcomingActivity.class);
                            intent.putExtra("cnt",callnum);
                            startActivity(intent);                        }
                    });

                }
            }, calltime);
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