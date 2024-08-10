package com.example.themovieapp.api
import com.example.themovieapp.dataModel.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IMovieApiService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey : String) : Call<Movie>
}