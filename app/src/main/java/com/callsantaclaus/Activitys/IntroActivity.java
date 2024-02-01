package com.callsantaclaus.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.callsantaclaus.ADS.AdsManager;
import com.callsantaclaus.ADS.InterstitialAD;
import com.callsantaclaus.ADS.Native;
import com.callsantaclaus.Adapters.Intro_Adapter;
import com.callsantaclaus.R;
import com.callsantaclaus.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {
    ActivityIntroBinding binding;
    int position =0;
    InterstitialAD helper;
    AdsManager adsManager = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        Intro_Adapter pagerAdapter = new Intro_Adapter(IntroActivity.this);
        binding.viewPager.setAdapter(pagerAdapter);

        Native aNative = new Native(this);
        aNative.ShowNative(this, findViewById(R.id.native_container),1);

//        Log.d(TAG, "onCreate: nativ = "+Show);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    binding.dd1.setVisibility(View.VISIBLE);
                    binding.dd2.setVisibility(View.INVISIBLE);
                    binding.dd3.setVisibility(View.INVISIBLE);
                }
                if (position == 1) {
                    binding.dd1.setVisibility(View.INVISIBLE);
                    binding.dd2.setVisibility(View.VISIBLE);
                    binding.dd3.setVisibility(View.INVISIBLE);
                }
                if (position == 2) {
                    binding.dd1.setVisibility(View.INVISIBLE);
                    binding.dd2.setVisibility(View.INVISIBLE);
                    binding.dd3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        binding.tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = binding.viewPager.getCurrentItem();
                position++;
                binding.viewPager.setCurrentItem(position);
                if (position == 3) {
                    helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                        @Override
                        public void onAdLoadFailed() {
                            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onInterstitialDismissed() {
                            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
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