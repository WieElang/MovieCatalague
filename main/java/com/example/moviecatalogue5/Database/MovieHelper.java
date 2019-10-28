package com.example.moviecatalogue5.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.moviecatalogue5.Model.Movie;

import java.util.ArrayList;
import java.util.List;

import static com.example.moviecatalogue5.Database.DbContract.DbColumns.MOVIE_TABLE;

public class MovieHelper {
    private static final String DATABASE_TABLE = MOVIE_TABLE;
    private final DbHelper dbHelper;
    private static MovieHelper INSTANCE;

    private SQLiteDatabase database;

    public MovieHelper(Context context) {
        dbHelper    = new DbHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();

        if (database.isOpen())
            database.close();
    }

    public void insertFavorite(Movie movie){
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbContract.DbColumns.ID,movie.getId());
        values.put(DbContract.DbColumns.TITLE,movie.getTitle());
        values.put(DbContract.DbColumns.DATE,movie.getReleaseDate());
        values.put(DbContract.DbColumns.DESCRIPTION,movie.getOverview());
        values.put(DbContract.DbColumns.POSTER_PATH,movie.getPosterPath());
        database.insert(MOVIE_TABLE,null,values);
        database.close();
    }
    public void insertFavoriteTv(Movie movie){
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbContract.DbColumns.ID,movie.getId());
        values.put(DbContract.DbColumns.NAME,movie.getName());
        values.put(DbContract.DbColumns.DESCRIPTION,movie.getOverview());
        values.put(DbContract.DbColumns.POSTER_PATH,movie.getPosterPath());

        database.insert(DbContract.TV_TABLE,null,values);
        database.close();
    }

    public void removeFavorite(int id){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(MOVIE_TABLE,DbContract.DbColumns.ID+"="+id,null);
    }
    public void removeFavoriteTv(int id){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(DbContract.TV_TABLE, DbContract.DbColumns.ID+"="+id,null);
    }

    public List<Movie> getAllFavorite(){
        String[] columns = {
                DbContract.DbColumns.ID,
                DbContract.DbColumns.TITLE,
                DbContract.DbColumns.DATE,
                DbContract.DbColumns.DESCRIPTION,
                DbContract.DbColumns.POSTER_PATH
        };
        String order = DbContract.DbColumns.TITLE+" ASC";
        List<Movie> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(MOVIE_TABLE,
                columns,
                null,
                null,
                null,
                null,
                order);

        if (cursor.moveToFirst()){
            do {
                Movie movie = new Movie();
                movie.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbContract.DbColumns.ID))));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(DbContract.DbColumns.TITLE)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(DbContract.DbColumns.DATE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndex(DbContract.DbColumns.DESCRIPTION)));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndex(DbContract.DbColumns.POSTER_PATH)));

                list.add(movie);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public List<Movie> getAllFavoriteTv(){
        String[] columns = {
                DbContract.DbColumns.ID,
                DbContract.DbColumns.DESCRIPTION,
                DbContract.DbColumns.NAME,
                DbContract.DbColumns.POSTER_PATH
        };
        String order = DbContract.DbColumns.NAME+" ASC";
        List<Movie> list = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(DbContract.TV_TABLE,
                columns,
                null,
                null,
                null,
                null,
                order);

        if (cursor.moveToFirst()){
            do {
                Movie movie = new Movie();
                movie.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbContract.DbColumns.ID))));
                movie.setOverview(cursor.getString(cursor.getColumnIndex(DbContract.DbColumns.DESCRIPTION)));
                movie.setName(cursor.getString(cursor.getColumnIndex(DbContract.DbColumns.NAME)));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndex(DbContract.DbColumns.POSTER_PATH)));

                list.add(movie);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public Cursor provider(){
        return database.query(MOVIE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DbContract.DbColumns.ID+" ASC");
    }
}
