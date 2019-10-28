package com.example.moviecatalogue5.Interface;

import com.example.moviecatalogue5.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("discover/movie")
    Call<MovieResponse> getDiscoverMovie(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<MovieResponse> getDiscoverTv(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieResponse> getSearchMovie(@Query("api_key") String apiKey,@Query("query")String title);

    @GET("search/tv")
    Call<MovieResponse> getSearchTv(@Query("api_key")String apiKey,@Query("query")String name);

    @GET("discover/movie")
    Call<MovieResponse> getReleaseToday(@Query("api_key")String apiKey, @Query("primary_release_date.gte")String date1, @Query("primary_release_date.lte")String date2);
}
