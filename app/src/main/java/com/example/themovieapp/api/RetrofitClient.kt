package com.example.themovieapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private val retrofitBuilder by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMovieApiService::class.java)
    }

    object MovieList {
        val movieList = retrofitBuilder.getPopularMovies("f0f9da663fc666f530266581f6546612")
    }
}