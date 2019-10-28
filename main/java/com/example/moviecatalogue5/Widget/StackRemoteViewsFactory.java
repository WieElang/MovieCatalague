package com.example.moviecatalogue5.Widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.example.moviecatalogue5.Database.MovieHelper;
import com.example.moviecatalogue5.Model.Movie;
import com.example.moviecatalogue5.R;

import java.util.ArrayList;
import java.util.List;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private List<Movie> movies;
    private final Context context;

    public StackRemoteViewsFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        MovieHelper helper = new MovieHelper(context);
        movies = new ArrayList<>(helper.getAllFavorite());
    }

    @Override
    public void onDataSetChanged() {
        MovieHelper helper = new MovieHelper(context);
        movies = new ArrayList<>(helper.getAllFavorite());
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        try {
            Bitmap bitmap = Glide.with(context)
                    .asBitmap()
                    .load("https://image.tmdb.org/t/p/w154/"+movies.get(position).getPosterPath())
                    .submit(150,200)
                    .get();
            remoteViews.setImageViewBitmap(R.id.widget_image,bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bundle extras = new Bundle();
        extras.putString(MovieAppWidget.EXTRA_ITEM,movies.get(position).getTitle());
        Intent intent = new Intent();
        intent.putExtras(extras);
        remoteViews.setOnClickFillInIntent(R.id.widget_image,intent);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
