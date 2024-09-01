package com.example.themovieapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    // Lazy initialization of the Retrofit instance
    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Lazy initialization of the API service
    val movieApiService: IMovieApiService by lazy {
        retrofit.create(IMovieApiService::class.java)
    }

}
