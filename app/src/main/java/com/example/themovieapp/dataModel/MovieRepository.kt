package com.example.themovieapp.dataModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.themovieapp.activities.MainActivity
import com.example.themovieapp.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    private val mutableLiveData: MutableLiveData<List<Result>> = MutableLiveData()
    private lateinit var call: Call<Movie>


    fun getMutableLiveData(movieType: String): MutableLiveData<List<Result>> {
        val movieApiService = RetrofitClient.movieApiService

        when (movieType.lowercase()) {
            "popular" -> call = movieApiService.getPopularMovies("f0f9da663fc666f530266581f6546612")
            "upcoming" -> call =
                movieApiService.getUpComingMovies("f0f9da663fc666f530266581f6546612")

            "top-rated" -> call =
                movieApiService.getTopRatedMovies("f0f9da663fc666f530266581f6546612")

            "now-playing" -> call =
                movieApiService.getNowPlayingMovies("f0f9da663fc666f530266581f6546612")

            else -> Log.d(MainActivity.TAG, "onItemSelected: No matching movie type")
        }

        call.enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                Log.d(TAG, "onResponse: response = $response")
                if (response.isSuccessful) {
                    val list = response.body()?.results
                    if (!list.isNullOrEmpty()) {
                        Log.d(TAG, "onResponse: Received ${list.size} movies")
                        mutableLiveData.value = list!!
                    } else {
                        Log.d(TAG, "onResponse: No movies found in response")
                    }
                } else {
                    Log.e(TAG, "onResponse: Failed response, ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Movie?>, response: Throwable) {
                Log.e(TAG, "onFailure: API call failed, ${response.message}")
            }
        })
        return mutableLiveData
    }
}