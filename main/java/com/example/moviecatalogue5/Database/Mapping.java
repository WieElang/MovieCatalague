package com.example.moviecatalogue5.Database;

import android.database.Cursor;

import com.example.moviecatalogue5.Model.Movie;

import java.util.ArrayList;

import static com.example.moviecatalogue5.Database.DbContract.DbColumns.ID;
import static com.example.moviecatalogue5.Database.DbContract.DbColumns.TITLE;
import static com.example.moviecatalogue5.Database.DbContract.DbColumns.DATE;
import static com.example.moviecatalogue5.Database.DbContract.DbColumns.DESCRIPTION;
import static com.example.moviecatalogue5.Database.DbContract.DbColumns.NAME;
import static com.example.moviecatalogue5.Database.DbContract.DbColumns.POSTER_PATH;

public class Mapping {
    public static ArrayList<Movie> mapCursor(Cursor cursor){
        ArrayList<Movie> movies = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE));
            String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(DATE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
            String posterPath = cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH));

            movies.add(new Movie(id,title,releaseDate,overview,name,posterPath));
        }
        return movies;
    }
}
