package com.callsantaclaus.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.callsantaclaus.Activitys.ScheduleActivity;
import com.callsantaclaus.R;

public class CustomDialogClass extends Dialog {

  public Activity c;
    TextView schedule;
  public TextView t1, t2,t3,t4,t5,t6;

  public CustomDialogClass(Activity a, TextView schedule) {
    super(a);
    this.c = a;
    this.schedule=schedule;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.schedule_dialog);

    t1 = findViewById(R.id.textView);
    t2 = findViewById(R.id.textView2);
    t3 = findViewById(R.id.textView3);
    t4 = findViewById(R.id.textView4);
    t5 = findViewById(R.id.textView5);
    t6 = findViewById(R.id.textView6);

    t1.setOnClickListener(view -> {
        schedule.setText("Instant");
        ScheduleActivity.calltime = 1000;
        dismiss();
    });
    t2.setOnClickListener(view -> {
        schedule.setText("After 5 seconds");
        ScheduleActivity.calltime = 5000;
        dismiss();
    });
    t3.setOnClickListener(view -> {
        schedule.setText("After 10 seconds");
        ScheduleActivity.calltime = 10000;
        dismiss();
    });
    t4.setOnClickListener(view -> {
        schedule.setText("After 15 seconds");
        ScheduleActivity.calltime = 15000;
        dismiss();
    });
    t5.setOnClickListener(view -> {
        schedule.setText("After 20 seconds");
        ScheduleActivity.calltime = 20000;
        dismiss();
    });
    t6.setOnClickListener(view -> {
        schedule.setText("After 30 seconds");
        ScheduleActivity.calltime = 30000;
        dismiss();
    });

  }
}