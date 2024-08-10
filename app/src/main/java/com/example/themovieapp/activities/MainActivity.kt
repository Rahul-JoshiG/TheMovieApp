package com.example.themovieapp.activities

import android.os.Bundle
import android.util.Log
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

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Log.d(TAG, "onCreate: ")

        movieApiCall()
    }

    private fun movieApiCall() {
        RetrofitClient.MovieList.movieList.enqueue(object : Callback<Movie?> {
            override fun onResponse(p0: Call<Movie?>, p1: Response<Movie?>) {
                Log.d(TAG, "onResponse: response $p1")
                val movieList = p1.body()?.results
                setUpRecyclerView(movieList!!)
            }

            override fun onFailure(p0: Call<Movie?>, p1: Throwable) {
                Log.d(TAG, "onFailure: ${p1.message}")
                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpRecyclerView(list: List<Result>) {
        Log.d(TAG, "setUpRecyclerView: ")
        val movieAdapter = MovieAdapter(list)
        mBinding.movieRecyclerView.adapter = movieAdapter
        mBinding.movieRecyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.movieRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }


    companion object {
        private const val TAG = "MainActivity"
    }
}
