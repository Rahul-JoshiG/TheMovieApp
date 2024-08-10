package com.example.themovieapp.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.adapters.MovieAdapter
import com.example.themovieapp.api.RetrofitClient
import com.example.themovieapp.dataModel.Movie
import com.example.themovieapp.dataModel.Result
import com.example.themovieapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Log.d(TAG, "onCreate: Activity started")

        setUpSpinner()
    }

    private fun setUpSpinner() {
        Log.d(TAG, "setUpSpinner: Setting up spinner")
        ArrayAdapter.createFromResource(
            this,
            R.array.movie_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mBinding.movieTypeSpinner.adapter = adapter
        }

        showMoviesType()
    }

    private fun showMoviesType() {
        Log.d(TAG, "showMovies: Setting up movie's type display")
        mBinding.movieTypeSpinner.onItemSelectedListener = this
    }

    override fun onItemSelected(call: AdapterView<*>?, response: View?, p2: Int, p3: Long) {
        val type = call?.getItemAtPosition(p2).toString()
        Log.d(TAG, "onItemSelected: Selected movie type $type")
        when (type.lowercase()) {
            "popular" -> movieApiCall(RetrofitClient.MovieList.getPopularMovies())
            "upcoming" -> movieApiCall(RetrofitClient.MovieList.getUpcomingMovies())
            "top-rated" -> movieApiCall(RetrofitClient.MovieList.getTopRatedMovies())
            "now-playing" -> movieApiCall(RetrofitClient.MovieList.getNowPlayingMovies())
            else -> Log.d(TAG, "onItemSelected: No matching movie type")
        }
    }

    override fun onNothingSelected(call: AdapterView<*>?) {
        Log.d(TAG, "onNothingSelected: No movie type selected")
    }

    private fun movieApiCall(movieList: Call<Movie>) {
        Log.d(TAG, "movieApiCall: Making API call")
        movieList.enqueue(object : Callback<Movie?> {
            override fun onResponse(call: Call<Movie?>, response: Response<Movie?>) {
                if (response.isSuccessful && response.body()?.results != null) {
                    val list = response.body()?.results
                    if (!list.isNullOrEmpty()) {
                        Log.d(TAG, "onResponse: Received ${list.size} movies")
                        setUpRecyclerView(list)
                    } else {
                        Log.d(TAG, "onResponse: No movies found in response")
                        Toast.makeText(this@MainActivity, "No movies found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e(TAG, "onResponse: Failed response, ${response.message()}")
                    Toast.makeText(this@MainActivity, "Failed to load movies", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Movie?>, response: Throwable) {
                Log.e(TAG, "onFailure: API call failed, ${response.message}")
                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpRecyclerView(list: List<Result>) {
        Log.d(TAG, "setUpRecyclerView: Initializing RecyclerView with ${list.size} movies")
        val movieAdapter = MovieAdapter(list)
        mBinding.movieRecyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
