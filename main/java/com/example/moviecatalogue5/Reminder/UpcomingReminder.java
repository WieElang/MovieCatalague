package com.example.moviecatalogue5.Reminder;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.moviecatalogue5.Activity.MainActivity;
import com.example.moviecatalogue5.BuildConfig;
import com.example.moviecatalogue5.Model.Movie;
import com.example.moviecatalogue5.Model.MovieResponse;
import com.example.moviecatalogue5.R;
import com.example.moviecatalogue5.Repository.MovieRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingReminder {
    private static final String EXTRA_TYPE="type";
    private static final String CHANNEL_ID="channel_02";
    private static final String CHANNEL_NAME="upcoming_reminder";
    private static int ID_RELEASE = 101;
    static List<Movie> movies;

    @SuppressLint("NewApi")
    static void showUpcomingNotification(final Context context){

        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(intent);
        final PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(ID_RELEASE, PendingIntent.FLAG_UPDATE_CURRENT);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String time = simpleDateFormat.format(date);

        MovieRepository.apiInterface().getReleaseToday(BuildConfig.API_KEY,time,time).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null){
                    movies = response.body().getResults();
                    for (int x =0;x<movies.size();x++){
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle(movies.get(x).getTitle())
                                .setContentText(movies.get(x).getTitle()+" Release Now!")
                                .setContentIntent(pendingIntent)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                            builder.setChannelId(CHANNEL_ID);
                            if (notificationManager != null) {
                                notificationManager.createNotificationChannel(channel);
                            }
                        }
                        notificationManager.notify(x,builder.build());

                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }

    public static void setReminder(Context context){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,8);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        Intent intent = new Intent(context,Receiver.class);
        intent.putExtra(EXTRA_TYPE,"upcoming");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_RELEASE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if (alarmManager != null){
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
        }
    }

    public static void cancelReminder(Context context,Class<?> cls){
        Intent intent = new Intent(context,cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_RELEASE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent.cancel();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null){
            alarmManager.cancel(pendingIntent);
        }
    }
}
