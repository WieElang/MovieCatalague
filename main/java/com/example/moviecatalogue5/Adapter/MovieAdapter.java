package com.example.moviecatalogue5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogue5.Activity.DetailActivity;
import com.example.moviecatalogue5.Model.Movie;
import com.example.moviecatalogue5.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private String IMAGE_URL = "http://image.tmdb.org/t/p/w500";
    private List<Movie> movies;
    private int item_all;
    private Context context;

    public MovieAdapter(List<Movie> movies, int item_all, Context context) {
        this.movies = movies;
        this.item_all = item_all;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_all,viewGroup,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, final int i) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.spinner)
                .error(R.drawable.errorimage)
                .apply(new RequestOptions().fitCenter());

        Glide.with(movieViewHolder.itemView.getContext())
                .load(IMAGE_URL + movies.get(i).getPosterPath())
                .apply(options)
                .into(movieViewHolder.imgView);

        movieViewHolder.tvTitle.setText(movies.get(i).getTitle());
        movieViewHolder.tvName.setText(movies.get(i).getName());
        movieViewHolder.tvReleaseDate.setText(movies.get(i).getReleaseDate());

        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("KEY", movies.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;
        TextView tvTitle,tvReleaseDate,tvName;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
            tvName = itemView.findViewById(R.id.tv_name);

        }
    }
}
