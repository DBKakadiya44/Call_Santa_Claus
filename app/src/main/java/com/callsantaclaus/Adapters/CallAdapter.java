package com.callsantaclaus.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.callsantaclaus.ADS.AdsManager;
import com.callsantaclaus.ADS.InterstitialAD;
import com.callsantaclaus.Activitys.CallActivity;
import com.callsantaclaus.Activitys.ScheduleActivity;
import com.callsantaclaus.R;

import org.w3c.dom.Text;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ViewHolder>
{
    int[] santaimage = {R.drawable.sa1,R.drawable.sa2,R.drawable.sa3,R.drawable.sa4,R.drawable.sa5,R.drawable.sa6,R.drawable.sa7,
            R.drawable.sa8};
    CallActivity callActivity;
    InterstitialAD helper;
    AdsManager adsManager = null;
    public CallAdapter(CallActivity callActivity) {
        this.callActivity=callActivity;
    }

    @NonNull
    @Override
    public CallAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_call,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallAdapter.ViewHolder holder, int position) {
        adsManager = new AdsManager(callActivity);
        helper = new InterstitialAD(callActivity,callActivity,adsManager);

        holder.imageView.setImageResource(R.drawable.santaimg);
        int pos = position+1;
        holder.textView.setText("Santa Claus "+pos);

        holder.imageView.setOnClickListener(view -> {
            helper.showCounterInterstitialAd(new InterstitialAD.AdLoadListeners() {
                @Override
                public void onAdLoadFailed() {
                    Intent intent = new Intent(callActivity , ScheduleActivity.class);
                    intent.putExtra("cnt",position);
                    callActivity.startActivity(intent);                }

                @Override
                public void onInterstitialDismissed() {
                    Intent intent = new Intent(callActivity , ScheduleActivity.class);
                    intent.putExtra("cnt",position);
                    callActivity.startActivity(intent);                }
            });

        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.callImage);
            textView = itemView.findViewById(R.id.itemtextview);
        }
    }
}
