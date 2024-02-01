package com.callsantaclaus.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.callsantaclaus.ADS.AdsManager;
import com.callsantaclaus.ADS.InterstitialAD;
import com.callsantaclaus.ADS.Native;
import com.callsantaclaus.R;
import com.callsantaclaus.databinding.ActivityLatterBinding;

public class LatterActivity extends AppCompatActivity {
    ActivityLatterBinding binding;
    InterstitialAD helper;
    AdsManager adsManager = null;
    int theme = 1, letter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLatterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        Native aNative = new Native(this);
        aNative.ShowNative(this, findViewById(R.id.native_container),2);

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);


        binding.tt1.setOnClickListener(view -> {
            clearcolor();
            binding.tt1.setBackgroundResource(R.drawable.dark_dot);
            letter = 1;
            setletter();
        });
        binding.tt2.setOnClickListener(view -> {
            clearcolor();
            binding.tt2.setBackgroundResource(R.drawable.dark_dot);
            letter = 2;
            setletter();
        });
        binding.tt3.setOnClickListener(view -> {
            clearcolor();
            binding.tt3.setBackgroundResource(R.drawable.dark_dot);
            letter = 3;
            setletter();
        });
        binding.tt4.setOnClickListener(view -> {
            clearcolor();
            binding.tt4.setBackgroundResource(R.drawable.dark_dot);
            letter = 4;
            setletter();
        });
        binding.tt5.setOnClickListener(view -> {
            clearcolor();
            binding.tt5.setBackgroundResource(R.drawable.dark_dot);
            letter = 5;
            setletter();
        });

        binding.sendbtn.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    startActivity(new Intent(LatterActivity.this, ThanksActivity.class));
                }

                @Override
                public void onInterstitialDismissed() {
                    startActivity(new Intent(LatterActivity.this, ThanksActivity.class));
                }
            });
        });

        binding.letterchange.setOnClickListener(view -> {
            if(theme>=1 && theme<6){
                if(theme==5){
                    theme=0;
                }
                theme++;
                setletter();
            }
        });

        binding.previewbtn.setOnClickListener(view -> {
            Intent intent = new Intent(LatterActivity.this, LetterPreviewActivity.class);
            intent.putExtra("t",theme);
            intent.putExtra("s",letter);
            startActivity(intent);
        });

    }

    private void setletter() {
        if (theme == 1 && letter == 1) {
            binding.imageView6.setImageResource(R.drawable.t1s1);
        }
        if (theme == 1 && letter == 2) {
            binding.imageView6.setImageResource(R.drawable.t1s2);
        }
        if (theme == 1 && letter == 3) {
            binding.imageView6.setImageResource(R.drawable.t1s3);
        }
        if (theme == 1 && letter == 4) {
            binding.imageView6.setImageResource(R.drawable.t1s4);
        }
        if (theme == 1 && letter == 5) {
            binding.imageView6.setImageResource(R.drawable.t1s5);
        }

        if (theme == 2 && letter == 1) {
            binding.imageView6.setImageResource(R.drawable.t2s1);
        }
        if (theme == 2 && letter == 2) {
            binding.imageView6.setImageResource(R.drawable.t2s2);
        }
        if (theme == 2 && letter == 3) {
            binding.imageView6.setImageResource(R.drawable.t2s3);
        }
        if (theme == 2 && letter == 4) {
            binding.imageView6.setImageResource(R.drawable.t2s4);
        }
        if (theme == 2 && letter == 5) {
            binding.imageView6.setImageResource(R.drawable.t2s5);
        }

        if (theme == 3 && letter == 1) {
            binding.imageView6.setImageResource(R.drawable.t3s1);
        }
        if (theme == 3 && letter == 2) {
            binding.imageView6.setImageResource(R.drawable.t3s2);
        }
        if (theme == 3 && letter == 3) {
            binding.imageView6.setImageResource(R.drawable.t3s3);
        }
        if (theme == 3 && letter == 4) {
            binding.imageView6.setImageResource(R.drawable.t3s4);
        }
        if (theme == 3 && letter == 5) {
            binding.imageView6.setImageResource(R.drawable.t3s5);
        }

        if (theme == 4 && letter == 1) {
            binding.imageView6.setImageResource(R.drawable.t4s1);
        }
        if (theme == 4 && letter == 2) {
            binding.imageView6.setImageResource(R.drawable.t4s2);
        }
        if (theme == 4 && letter == 3) {
            binding.imageView6.setImageResource(R.drawable.t4s3);
        }
        if (theme == 4 && letter == 4) {
            binding.imageView6.setImageResource(R.drawable.t4s4);
        }
        if (theme == 4 && letter == 5) {
            binding.imageView6.setImageResource(R.drawable.t4s5);
        }

        if (theme == 5 && letter == 1) {
            binding.imageView6.setImageResource(R.drawable.t5s1);
        }
        if (theme == 5 && letter == 2) {
            binding.imageView6.setImageResource(R.drawable.t5s2);
        }
        if (theme == 5 && letter == 3) {
            binding.imageView6.setImageResource(R.drawable.t5s3);
        }
        if (theme == 5 && letter == 4) {
            binding.imageView6.setImageResource(R.drawable.t5s4);
        }
        if (theme == 5 && letter == 5) {
            binding.imageView6.setImageResource(R.drawable.t5s5);
        }
    }

    private void clearcolor() {
        binding.tt1.setBackgroundResource(R.drawable.new_border);
        binding.tt2.setBackgroundResource(R.drawable.new_border);
        binding.tt3.setBackgroundResource(R.drawable.new_border);
        binding.tt4.setBackgroundResource(R.drawable.new_border);
        binding.tt5.setBackgroundResource(R.drawable.new_border);
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