package com.callsantaclaus.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.callsantaclaus.ADS.AdsManager;
import com.callsantaclaus.ADS.InterstitialAD;
import com.callsantaclaus.ADS.Native;
import com.callsantaclaus.Adapters.CallAdapter;
import com.callsantaclaus.R;
import com.callsantaclaus.databinding.ActivityCallBinding;

public class CallActivity extends AppCompatActivity {
    ActivityCallBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this, this, adsManager);

        Native aNative = new Native(this);
        aNative.ShowNative(this, findViewById(R.id.native_container), 1);

        binding.ivBack.setOnClickListener(view -> {
            onBackPressed();
        });

        CallAdapter adapter = new CallAdapter(CallActivity.this);
        RecyclerView.LayoutManager manager = new GridLayoutManager(CallActivity.this, 2);
        binding.callrecycler.setLayoutManager(manager);
        binding.callrecycler.setAdapter(adapter);

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