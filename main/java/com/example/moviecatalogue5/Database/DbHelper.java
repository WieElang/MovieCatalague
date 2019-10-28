package com.example.moviecatalogue5.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.moviecatalogue5.Database.DbContract.DbColumns.MOVIE_TABLE;

public class DbHelper extends SQLiteOpenHelper {
    public static String DATBASE_NAME = "dbmoviecatalogue";
    private static final int DATBASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context,DATBASE_NAME,null,DATBASE_VERSION);
    }

    private static final String SQL_CREATE_TABLE = String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL)",
            MOVIE_TABLE,
            DbContract.DbColumns.ID,
            DbContract.DbColumns.TITLE,
            DbContract.DbColumns.DATE,
            DbContract.DbColumns.DESCRIPTION,
            DbContract.DbColumns.POSTER_PATH
    );

    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s"
                    +"(%s INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL,"+
                    "%s TEXT NOT NULL)",
            DbContract.TV_TABLE,
            DbContract.DbColumns.ID,
            DbContract.DbColumns.NAME,
            DbContract.DbColumns.DESCRIPTION,
            DbContract.DbColumns.POSTER_PATH
    );

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ MOVIE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+ DbContract.TV_TABLE);
        onCreate(db);
    }
}
