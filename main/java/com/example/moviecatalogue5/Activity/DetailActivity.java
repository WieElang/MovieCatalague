package com.example.moviecatalogue5.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogue5.Database.MovieHelper;
import com.example.moviecatalogue5.Model.Movie;
import com.example.moviecatalogue5.R;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private String IMAGE_URL = "http://image.tmdb.org/t/p/w500";
    private int id;
    private String poster, title, overview, release_date, name;
    private TextView tvTitle, tvDate, tvDescription, tvName;
    private ImageView imgBackground,imgPost;
    private Button btnFavorite,btnDelete;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);

        tvTitle = findViewById(R.id.tv_title_detail);
        tvDate = findViewById(R.id.tv_date_detail);
        tvDescription = findViewById(R.id.tv_description);
        imgBackground = findViewById(R.id.img_background);
        imgPost = findViewById(R.id.img_post);
        tvName = findViewById(R.id.tv_name_detail);
        btnFavorite = findViewById(R.id.btn_favorite);
        btnDelete = findViewById(R.id.btn_delete);

        movie = new Movie();
        movie = getIntent().getParcelableExtra("KEY");
        if (movie != null) {
            id = movie.getId();
            poster = movie.getPosterPath();
            title = movie.getTitle();
            name = movie.getName();
            overview = movie.getOverview();
            release_date = movie.getReleaseDate();
        }

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.spinner)
                .error(R.drawable.errorimage);

        Glide.with(getApplicationContext())
                .load(IMAGE_URL + poster)
                .apply(options)
                .into(imgBackground);

        Glide.with(getApplicationContext())
                .load(IMAGE_URL + poster)
                .apply(options)
                .into(imgPost);


        tvTitle.setText(title);
        tvName.setText(name);
        tvDate.setText(release_date);
        tvDescription.setText(overview);

        btnFavorite.setOnClickListener(this);
        btnDelete.setOnClickListener(this);


        if (name == null) {
            DetailActivity.this.setTitle(title + "("+ release_date + ")");
        }else {
            DetailActivity.this.setTitle(name);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnFavorite){
            if (name == null) {
                insertMovie();
                Toast.makeText(getApplicationContext(), title + " + Favorit", Toast.LENGTH_LONG).show();
            }if (title == null){
                insertTv();
                Toast.makeText(getApplicationContext(), name + " + Favorit", Toast.LENGTH_LONG).show();
            }
        }
        if (v == btnDelete){
            if (name == null) {
                removeMovie();
                Toast.makeText(getApplicationContext(), title + " Removed From Favorit", Toast.LENGTH_LONG).show();
            }if (title == null){
                removeTv();
                Toast.makeText(getApplicationContext(), name + " Removed From Favorit", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void insertMovie(){
        MovieHelper helper = new MovieHelper(DetailActivity.this);
        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setReleaseDate(release_date);
        movie.setOverview(overview);
        movie.setPosterPath(poster);
        helper.insertFavorite(movie);
    }
    public void insertTv(){
        MovieHelper helper = new MovieHelper(DetailActivity.this);
        Movie movie = new Movie();
        movie.setId(id);
        movie.setOverview(overview);
        movie.setName(name);
        movie.setPosterPath(poster);
        helper.insertFavoriteTv(movie);
    }

    public void removeMovie(){
        MovieHelper helper = new MovieHelper(DetailActivity.this);
        helper.removeFavorite(id);
    }
    public void removeTv(){
        MovieHelper helper = new MovieHelper(DetailActivity.this);
        helper.removeFavoriteTv(id);
    }
}
