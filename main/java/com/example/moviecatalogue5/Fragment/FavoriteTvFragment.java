package com.example.moviecatalogue5.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecatalogue5.Adapter.FavoriteAdapter;
import com.example.moviecatalogue5.Database.DbHelper;
import com.example.moviecatalogue5.Database.MovieHelper;
import com.example.moviecatalogue5.Model.Movie;
import com.example.moviecatalogue5.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvFragment extends Fragment {
    private RecyclerView recyclerView;
    private DbHelper helper;


    public FavoriteTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_tv, container, false);

        recyclerView = view.findViewById(R.id.rv_favorite_tv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        MovieHelper helper = new MovieHelper(getContext());
        List<Movie> movies = new ArrayList<>(helper.getAllFavoriteTv());
        recyclerView.setAdapter(new FavoriteAdapter(movies,R.layout.item_favorite,getContext()));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MovieHelper helper = new MovieHelper(getContext());
        List<Movie> movies = new ArrayList<>(helper.getAllFavoriteTv());
        recyclerView.setAdapter(new FavoriteAdapter(movies,R.layout.item_favorite,getContext()));
    }
}
