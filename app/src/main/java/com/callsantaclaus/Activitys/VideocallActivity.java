package com.callsantaclaus.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.VideoView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.callsantaclaus.ADS.AdsManager;
import com.callsantaclaus.ADS.InterstitialAD;
import com.callsantaclaus.R;
import com.callsantaclaus.databinding.ActivityVideocallBinding;
import org.json.JSONArray;
import java.util.Random;

public class VideocallActivity extends AppCompatActivity implements SurfaceHolder.Callback {
ActivityVideocallBinding binding;
    private VideoView videoplay;
    ImageView imgCallend, imgMicPlay,  imgMute;
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private boolean isFrontCamera = false;
    private RequestQueue requestQueue;
    private StringBuilder videoString;
    private JSONArray videoArray;
    int time;
    int min=0;
    InterstitialAD helper;
    AdsManager adsManager = null;
    private String[] videoFileNames = {
            "https://moviescraft.com/Videocallapp/v1.mp4", "https://moviescraft.com/Videocallapp/v2.mp4", "https://moviescraft.com/Videocallapp/v3.mp4",
            "https://moviescraft.com/Videocallapp/v4.mp4", "https://moviescraft.com/Videocallapp/v5.mp4", "https://moviescraft.com/Videocallapp/v6.mp4",
            "https://moviescraft.com/Videocallapp/v7.mp4", "https://moviescraft.com/Videocallapp/v8.mp4", "https://moviescraft.com/Videocallapp/v9.mp4",
            "https://moviescraft.com/Videocallapp/v10.mp4","https://moviescraft.com/Videocallapp/v11.mp4" };
    private MediaPlayer mediaPlayer;
    private boolean mVolumePlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideocallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.maincolor));

        adsManager = new AdsManager(this);
        helper = new InterstitialAD(this,this,adsManager);

        settimer();

        imgCallend = findViewById(R.id.imgCallend);
        imgMicPlay = findViewById(R.id.imgMicPlay);
        imgMute = findViewById(R.id.imgMute);
        videoplay = (VideoView) findViewById(R.id.videoplay);
        surfaceView = findViewById(R.id.surfaceView);

        requestQueue = Volley.newRequestQueue(this);
        videoString = new StringBuilder();
        videoArray = new JSONArray();

        int randomIndex = new Random().nextInt(videoFileNames.length);
        String videoFileName = videoFileNames[randomIndex];

        videoplay.setVideoURI(Uri.parse(videoFileName));
        videoplay.start();

        videoplay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(VideocallActivity.this);

        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        imgCallend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imgMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchCamera();
            }
        });



    }

    private class VideoTouchListener implements VideoView.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (camera == null) {
            if (checkCameraHardware()) {
                openCamera();
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        releaseCamera();
    }

    private boolean checkCameraHardware() {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }

    private void openCamera() {
        try {
            if (isFrontCamera) {
                camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
            } else {
                camera = Camera.open();
            }
            camera.setDisplayOrientation(90);
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    private void switchCamera() {
        releaseCamera();
        isFrontCamera = !isFrontCamera;
        openCamera();
    }

    @Override
    public void onBackPressed() {
        videoplay.stopPlayback();
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
        super.onBackPressed();
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
                        binding.textView9.setText("00:0"+time);
                    }else{
                        binding.textView9.setText("00:"+time);
                    }
                }else{
                    if(min<=9){
                        if(time<=9){
                            binding.textView9.setText("0"+min+":"+"0"+time);
                        }else{
                            binding.textView9.setText("0"+min+":"+time);
                        }
                    }else{
                        if(time<=9){
                            binding.textView9.setText(min+":"+"0"+time);
                        }else{
                            binding.textView9.setText(min+":"+time);
                        }
                    }

                }

                settimer();
            }
        }, 1000);
    }
}