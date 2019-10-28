package com.example.moviecatalogue5.Database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DbContract {
    static String TV_TABLE = "tv";
    public static final String AUTHORITY = "com.example.moviecatalogue5.Database";

    static public final class DbColumns implements BaseColumns {
        static public String MOVIE_TABLE = "movie";
        static public String ID = "id";
        static public String TITLE = "title";
        static public String DATE = "date";
        static public String DESCRIPTION = "description";
        static public String POSTER_PATH = "poster_path";
        static public String NAME = "name";
        public static final Uri CONTENT = new Uri.Builder().scheme("content")
                .authority(AUTHORITY)
                .appendPath(MOVIE_TABLE)
                .build();

    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }
}
