package com.example.moviecatalogue5.Reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.example.moviecatalogue5.Reminder.DailyReminder.showDailyNotification;
import static com.example.moviecatalogue5.Reminder.UpcomingReminder.showUpcomingNotification;

public class Receiver extends BroadcastReceiver {
    public static final String EXTRA_TYPE="type";

    @Override
    public void onReceive(Context context, Intent intent) {
        String type = intent.getStringExtra(EXTRA_TYPE);
        if (type != null && type.equalsIgnoreCase("upcoming")){
            showUpcomingNotification(context);
        }else if (type != null && type.equalsIgnoreCase("daily")){
            showDailyNotification(context);
        }
    }
}
