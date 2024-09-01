package com.example.themovieapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themovieapp.R
import com.example.themovieapp.adapters.MovieAdapter
import com.example.themovieapp.dataModel.Result
import com.example.themovieapp.databinding.ActivityMainBinding
import com.example.themovieapp.interfaces.IMovieActions
import com.example.themovieapp.util.Constant
import com.example.themovieapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, IMovieActions {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainActivityViewModel
    private var currentMovieType: String = "popular" // Default movie type

    private lateinit var fadeInAnimation: Animation
    private lateinit var fadeOutAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        Log.d(TAG, "onCreate: Activity started")

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        setUpSpinner()
        setUpRefreshListener()

        // Initialize with default movie type
        getMovies(currentMovieType)
    }

    private fun setUpRefreshListener() {
        mBinding.main.setColorSchemeResources(R.color.black)
        mBinding.main.setOnRefreshListener {
            // Refresh the list based on the currently selected movie type
            getMovies(currentMovieType)
        }
    }

    private fun setUpSpinner() {
        Log.d(TAG, "setUpSpinner: Setting up spinner")
        ArrayAdapter.createFromResource(
            this,
            R.array.movie_type,
            R.layout.custom_spinner_layout
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mBinding.movieTypeSpinner.adapter = adapter
        }

        showMoviesType()
    }

    private fun showMoviesType() {
        Log.d(TAG, "showMoviesType: Setting up movie's type display")
        mBinding.movieTypeSpinner.onItemSelectedListener = this
    }

    override fun onItemSelected(call: AdapterView<*>?, response: View?, p2: Int, p3: Long) {
        val type = call?.getItemAtPosition(p2).toString()
        Log.d(TAG, "onItemSelected: Selected movie type $type")
        currentMovieType = type.lowercase()

        // Start the fade out animation
        mBinding.movieRecyclerView.startAnimation(fadeOutAnimation)

        // Delay for animation to complete before fetching new data
        fadeOutAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                getMovies(currentMovieType)
            }
            override fun onAnimationRepeat(animation: Animation?) {}
        })
    }

    override fun onNothingSelected(call: AdapterView<*>?) {
        Log.d(TAG, "onNothingSelected: No movie type selected")
    }

    private fun getMovies(type: String) {
        Log.d(TAG, "getMovies: movie type = $type")
        mViewModel.getMovies(type).observe(this) { moviesFromLiveData ->
            val movies = ArrayList(moviesFromLiveData)
            // Apply fade in animation after data is loaded
            setUpRecyclerView(movies)
            mBinding.main.isRefreshing = false
        }
    }

    private fun setUpRecyclerView(list: List<Result>) {
        Log.d(TAG, "setUpRecyclerView: Initializing RecyclerView with ${list.size} movies")
        val movieAdapter = MovieAdapter(list, this@MainActivity)
        mBinding.movieRecyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            // Start the fade in animation
            startAnimation(fadeInAnimation)
        }
    }

    override fun openActivity(position: Int) {
        Log.d(TAG, "openActivity: open activity with position of $position")
        openMovieFragmentActivity(position)
    }

    private fun openMovieFragmentActivity(position: Int) {
        Log.d(TAG, "openMovieFragmentActivity: opening movie fragment activity with position $position")
        val intent = Intent(this@MainActivity, MovieFragmentActivity::class.java)
        intent.putExtra(Constant.MOVIE_TYPE, currentMovieType)
        intent.putExtra(Constant.MOVIE_POSITION, position)
        startActivity(intent)
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
