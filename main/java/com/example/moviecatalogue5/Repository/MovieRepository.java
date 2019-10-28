package com.example.moviecatalogue5.Repository;

import com.example.moviecatalogue5.Interface.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static <T> T builder(Class<T> endpoints){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(endpoints);
    }
    public static ApiInterface apiInterface(){
        return builder(ApiInterface.class);
    }
}
