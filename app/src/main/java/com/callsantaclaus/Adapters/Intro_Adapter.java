package com.callsantaclaus.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.callsantaclaus.Activitys.IntroActivity;
import com.callsantaclaus.R;


public class Intro_Adapter extends PagerAdapter
{
    IntroActivity introActivity;

    public Intro_Adapter(IntroActivity introActivity) {
        this.introActivity = introActivity;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(introActivity).inflate(R.layout.itemintro,container,false);
        container.addView(view);

        ImageView imageView = view.findViewById(R.id.ivMain);
        TextView title = view.findViewById(R.id.tvHeading);
        TextView desc = view.findViewById(R.id.tvDescription);

        if(position==0)
        {
            imageView.setImageResource(R.drawable.intro1);
            title.setText("Santa Claus Is Coming Town");
            desc.setText("Have a holly jolly Christmas");
        }
        else if(position==1)
        {
            imageView.setImageResource(R.drawable.intro2);
            title.setText("Make a Call With Santa Claus");
            desc.setText("Let’s call to Santa and make your wish");
        }
        else if (position==2)
        {
            imageView.setImageResource(R.drawable.intro3);
            title.setText("What Gift do you Wish for Christmas?");
            desc.setText("Write a letter to Santa Claus");
        }

        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
