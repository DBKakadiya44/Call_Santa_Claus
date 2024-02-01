package com.callsantaclaus.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.callsantaclaus.ADS.AdsManager;
import com.callsantaclaus.ADS.InterstitialAD;
import com.callsantaclaus.ADS.Native;
import com.callsantaclaus.Adapters.WallpaperAdapter;
import com.callsantaclaus.R;
import com.callsantaclaus.databinding.ActivityWallpaperBinding;

public class WallpaperActivity extends AppCompatActivity {
    ActivityWallpaperBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWallpaperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WallpaperAdapter adapter = new WallpaperAdapter(WallpaperActivity.this);
        RecyclerView.LayoutManager manager = new GridLayoutManager(WallpaperActivity.this,2);
        binding.wallrecycler.setLayoutManager(manager);
        binding.wallrecycler.setAdapter(adapter);

        Native aNative = new Native(this);
        aNative.ShowNative(this, findViewById(R.id.native_container),1);

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

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