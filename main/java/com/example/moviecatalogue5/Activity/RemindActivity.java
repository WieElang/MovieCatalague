package com.example.moviecatalogue5.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

import com.example.moviecatalogue5.R;
import com.example.moviecatalogue5.Reminder.DailyReminder;
import com.example.moviecatalogue5.Reminder.UpcomingReminder;

import java.util.Objects;

public class RemindActivity extends AppCompatActivity {
    private Switch dailySwitch,upcomingSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        upcomingSwitch = findViewById(R.id.upcoming_switch);
        dailySwitch = findViewById(R.id.daily_switch);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Reminder Settings");

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);

        final SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);

        dailySwitch.setChecked(sharedPreferences.getBoolean("daily_switch", false));
        dailySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (dailySwitch.isChecked()){
                    DailyReminder.setReminder(RemindActivity.this);
                    editor.putBoolean("daily_switch",true);
                    editor.apply();
                }else {
                    DailyReminder.cancelReminder(RemindActivity.this, DailyReminder.class);
                    editor.putBoolean("daily_switch",false);
                    editor.apply();
                }
            }
        });

        upcomingSwitch.setChecked(sharedPreferences.getBoolean("upcoming_switch",false));
        upcomingSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (upcomingSwitch.isChecked()){
                    UpcomingReminder.setReminder(RemindActivity.this);
                    editor.putBoolean("upcoming_switch",true);
                    editor.apply();
                }else {
                    UpcomingReminder.cancelReminder(RemindActivity.this, UpcomingReminder.class);
                    editor.putBoolean("upcoming_switch",false);
                    editor.apply();
                }
            }
        });
    }
}
