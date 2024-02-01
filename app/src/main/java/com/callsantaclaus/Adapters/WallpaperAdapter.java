package com.callsantaclaus.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.callsantaclaus.ADS.AdsManager;
import com.callsantaclaus.ADS.InterstitialAD;
import com.callsantaclaus.Activitys.WallpaperActivity;
import com.callsantaclaus.R;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.ViewHolder>
{
    int[] santaimage = {R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7,
            R.drawable.w8,R.drawable.w9,R.drawable.w10};
    WallpaperActivity wallpaperActivity;
    InterstitialAD helper;
    AdsManager adsManager = null;
    public WallpaperAdapter(WallpaperActivity wallpaperActivity) {
        this.wallpaperActivity=wallpaperActivity;
    }

    @NonNull
    @Override
    public WallpaperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallpaper,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperAdapter.ViewHolder holder, int position) {
        adsManager = new AdsManager(wallpaperActivity);
        helper = new InterstitialAD(wallpaperActivity,wallpaperActivity,adsManager);

        holder.imageView.setImageResource(santaimage[position]);

        holder.imageView.setOnClickListener(view -> {

            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                }

                @Override
                public void onInterstitialDismissed() {
                }
            });

//            Intent intent = new Intent(wallpaperActivity , PreviewActivity.class);
//            intent.putExtra("img",santaimage[position]);
//            wallpaperActivity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.callImage);
        }
    }
}
