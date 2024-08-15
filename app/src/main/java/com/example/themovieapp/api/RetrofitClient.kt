package com.example.themovieapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "f0f9da663fc666f530266581f6546612"

    // Lazy initialization of the Retrofit instance
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Lazy initialization of the API service
    val movieApiService: IMovieApiService by lazy {
        retrofit.create(IMovieApiService::class.java)
    }

    // Object containing methods to fetch the movie lists
    /*object MovieList {
        fun getPopularMovies() = movieApiService.getPopularMovies(API_KEY)
        fun getUpcomingMovies() = movieApiService.getUpComingMovies(API_KEY)
        fun getTopRatedMovies() = movieApiService.getTopRatedMovies(API_KEY)
        fun getNowPlayingMovies() = movieApiService.getNowPlayingMovies(API_KEY)
    }*/
}
