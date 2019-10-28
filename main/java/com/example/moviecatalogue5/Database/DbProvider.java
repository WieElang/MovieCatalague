package com.example.moviecatalogue5.Database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;

import static com.example.moviecatalogue5.Database.DbContract.AUTHORITY;

public class DbProvider extends ContentProvider {
    private SQLiteOpenHelper handler;
    private static final int MOVIE = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MovieHelper movieHelper;

    static {
        //content://com.example.moviecatalogue5.Database/movie
        uriMatcher.addURI(AUTHORITY, DbContract.DbColumns.MOVIE_TABLE,MOVIE);
    }

    @Override
    public boolean onCreate() {
        movieHelper = new MovieHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        movieHelper.open();
        Cursor cursor;
        switch (uriMatcher.match(uri)){
            case MOVIE:
                cursor = movieHelper.provider();
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String x = null;
        if (x==null) {
            System.out.println("Pakai Insert");
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
