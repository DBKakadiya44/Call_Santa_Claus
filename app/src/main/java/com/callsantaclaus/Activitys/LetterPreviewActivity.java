package com.callsantaclaus.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.callsantaclaus.R;
import com.callsantaclaus.databinding.ActivityLetterPreviewBinding;

public class LetterPreviewActivity extends AppCompatActivity {
    ActivityLetterPreviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLetterPreviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        int t = getIntent().getIntExtra("t", 1);
        int s = getIntent().getIntExtra("s", 1);

        if (t == 1 && s == 1) {
            binding.imgpreview.setImageResource(R.drawable.t1s1);
        }
        if (t == 1 && s == 2) {
            binding.imgpreview.setImageResource(R.drawable.t1s2);
        }
        if (t == 1 && s == 3) {
            binding.imgpreview.setImageResource(R.drawable.t1s3);
        }
        if (t == 1 && s == 4) {
            binding.imgpreview.setImageResource(R.drawable.t1s4);
        }
        if (t == 1 && s == 5) {
            binding.imgpreview.setImageResource(R.drawable.t1s5);
        }

        if (t == 2 && s == 1) {
            binding.imgpreview.setImageResource(R.drawable.t2s1);
        }
        if (t == 2 && s == 2) {
            binding.imgpreview.setImageResource(R.drawable.t2s2);
        }
        if (t == 2 && s == 3) {
            binding.imgpreview.setImageResource(R.drawable.t2s3);
        }
        if (t == 2 && s == 4) {
            binding.imgpreview.setImageResource(R.drawable.t2s4);
        }
        if (t == 2 && s == 5) {
            binding.imgpreview.setImageResource(R.drawable.t2s5);
        }

        if (t == 3 && s == 1) {
            binding.imgpreview.setImageResource(R.drawable.t3s1);
        }
        if (t == 3 && s == 2) {
            binding.imgpreview.setImageResource(R.drawable.t3s2);
        }
        if (t == 3 && s == 3) {
            binding.imgpreview.setImageResource(R.drawable.t3s3);
        }
        if (t == 3 && s == 4) {
            binding.imgpreview.setImageResource(R.drawable.t3s4);
        }
        if (t == 3 && s == 5) {
            binding.imgpreview.setImageResource(R.drawable.t3s5);
        }

        if (t == 4 && s == 1) {
            binding.imgpreview.setImageResource(R.drawable.t4s1);
        }
        if (t == 4 && s == 2) {
            binding.imgpreview.setImageResource(R.drawable.t4s2);
        }
        if (t == 4 && s == 3) {
            binding.imgpreview.setImageResource(R.drawable.t4s3);
        }
        if (t == 4 && s == 4) {
            binding.imgpreview.setImageResource(R.drawable.t4s4);
        }
        if (t == 4 && s == 5) {
            binding.imgpreview.setImageResource(R.drawable.t4s5);
        }

        if (t == 5 && s == 1) {
            binding.imgpreview.setImageResource(R.drawable.t5s1);
        }
        if (t == 5 && s == 2) {
            binding.imgpreview.setImageResource(R.drawable.t5s2);
        }
        if (t == 5 && s == 3) {
            binding.imgpreview.setImageResource(R.drawable.t5s3);
        }
        if (t == 5 && s == 4) {
            binding.imgpreview.setImageResource(R.drawable.t5s4);
        }
        if (t == 5 && s == 5) {
            binding.imgpreview.setImageResource(R.drawable.t5s5);
        }


        binding.screen.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}