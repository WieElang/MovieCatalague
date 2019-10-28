package com.example.moviecatalogue5.Model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.moviecatalogue5.Database.DbContract;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.example.moviecatalogue5.Database.DbContract.getColumnInt;
import static com.example.moviecatalogue5.Database.DbContract.getColumnString;

public class Movie implements Parcelable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("name")
    @Expose
    private String name;

    public Movie(int id, String posterPath, String overview, String title, String releaseDate, String name) {
        this.id = id;
        this.posterPath = posterPath;
        this.overview = overview;
        this.title = title;
        this.releaseDate = releaseDate;
        this.name = name;
    }

    public Movie(Cursor cursor){
        this.id = getColumnInt(cursor,DbContract.DbColumns.ID);
        this.title = getColumnString(cursor,DbContract.DbColumns.TITLE);
        this.releaseDate = getColumnString(cursor, DbContract.DbColumns.DATE);
        this.overview = getColumnString(cursor, DbContract.DbColumns.DESCRIPTION);
        this.name = getColumnString(cursor, DbContract.DbColumns.NAME);
        this.posterPath = getColumnString(cursor, DbContract.DbColumns.POSTER_PATH);
    }

    public Movie() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.posterPath);
        dest.writeString(this.overview);
        dest.writeString(this.title);
        dest.writeString(this.releaseDate);
        dest.writeString(this.name);
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.posterPath = in.readString();
        this.overview = in.readString();
        this.title = in.readString();
        this.releaseDate = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
