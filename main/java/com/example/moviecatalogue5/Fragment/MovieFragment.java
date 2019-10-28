package com.example.moviecatalogue5.Fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.moviecatalogue5.Adapter.MovieAdapter;
import com.example.moviecatalogue5.BuildConfig;
import com.example.moviecatalogue5.Model.Movie;
import com.example.moviecatalogue5.Model.MovieResponse;
import com.example.moviecatalogue5.R;
import com.example.moviecatalogue5.Repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private RecyclerView recyclerView = null;
    private List<Movie> movies;
    private ProgressBar progressBar;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = view.findViewById(R.id.rv_movie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        movies = new ArrayList<>();
        progressBar = view.findViewById(R.id.progress_movie);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {
            progressBar.setVisibility(View.VISIBLE);
            MovieRepository.apiInterface().getDiscoverMovie(BuildConfig.API_KEY).enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.body() != null) {
                        movies = response.body().getResults();
                    }

                    recyclerView.setAdapter(new MovieAdapter(movies, R.layout.item_all, getContext()));
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(),"Cannot Refresh",Toast.LENGTH_LONG).show();
                }
            });
        } else {
            movies.addAll(savedInstanceState.<Movie>getParcelableArrayList("Movie"));
            recyclerView.setAdapter(new MovieAdapter(movies, R.layout.item_all, getContext()));
        }


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("Movie", (ArrayList<? extends Parcelable>) movies);
    }

}
