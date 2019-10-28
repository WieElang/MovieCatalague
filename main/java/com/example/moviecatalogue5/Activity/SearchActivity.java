package com.example.moviecatalogue5.Activity;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviecatalogue5.Adapter.FavoriteAdapter;
import com.example.moviecatalogue5.BuildConfig;
import com.example.moviecatalogue5.Model.Movie;
import com.example.moviecatalogue5.Model.MovieResponse;
import com.example.moviecatalogue5.R;
import com.example.moviecatalogue5.Repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    public static String search;
    private RecyclerView recyclerView;
    private List<Movie> movies = new ArrayList<>();
    private ProgressBar progressBar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search = getIntent().getStringExtra("Search");
        SearchActivity.this.setTitle("Search "+search);

        progressBar = findViewById(R.id.progress_search);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.rv_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (savedInstanceState == null){
            MovieRepository.apiInterface().getSearchMovie(BuildConfig.API_KEY,search).enqueue(new retrofit2.Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    if (response.body() != null){
                        movies = response.body().getResults();
                    }
                    recyclerView.setAdapter(new FavoriteAdapter(movies, R.layout.item_all,context));
                    progressBar.setVisibility(View.GONE);

                }
                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Toast.makeText(context, "Not Found Movie", Toast.LENGTH_LONG).show();
                }
            });
            MovieRepository.apiInterface().getSearchTv(BuildConfig.API_KEY,search).enqueue(new retrofit2.Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    if (response.body() != null){
                        movies = response.body().getResults();
                    }
                    recyclerView.setAdapter(new FavoriteAdapter(movies,R.layout.item_favorite,context));
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Toast.makeText(context,"Not Found Tv",Toast.LENGTH_LONG).show();
                }
            });
        }else {
            movies.addAll(savedInstanceState.<Movie>getParcelableArrayList("SearchMovie"));
            recyclerView.setAdapter(new FavoriteAdapter(movies,R.layout.item_favorite,context));
            progressBar.setVisibility(View.GONE);
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("SearchMovie", (ArrayList<? extends Parcelable>) movies);
    }
}
