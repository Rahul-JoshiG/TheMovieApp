package com.example.themovieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themovieapp.dataModel.MovieRepository
import com.example.themovieapp.dataModel.Result

class MovieViewModel : ViewModel() {
    private val mMovieRepository = MovieRepository()

    fun getMovies(type : String) : MutableLiveData<List<Result>> {
        return mMovieRepository.getMutableLiveData(type)
    }
}