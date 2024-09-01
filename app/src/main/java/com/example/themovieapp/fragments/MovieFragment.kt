package com.example.themovieapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.themovieapp.dataModel.Result
import com.example.themovieapp.databinding.FragmentMovieBinding
import com.example.themovieapp.util.Constant
import com.example.themovieapp.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import kotlin.properties.Delegates

class MovieFragment : Fragment() {

    private lateinit var mBinding: FragmentMovieBinding
    private lateinit var mViewModel : MovieViewModel
    private lateinit var mType: String
    private var mPosition by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: on create method of movie fragment")
        extractDataFromBaseActivity()
    }

    private fun extractDataFromBaseActivity() {
        Log.d(TAG, "extractDataFromBaseActivity: extracting data from movie fragment activity")
        val data = arguments
        mType = data?.getString(Constant.MOVIE_TYPE).toString()
        mPosition = data?.getInt(Constant.MOVIE_POSITION).toString().toInt()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: on create view in movie fragment")
        mBinding =
            FragmentMovieBinding.inflate(LayoutInflater.from(inflater.context), container, false)
        mViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: view created ")
        extractListFromViewModel()
    }

    private fun extractListFromViewModel(){
        Log.d(TAG, "extractListFromViewModel: extracting data list from view model")
        mViewModel.getMovies(mType).observe(viewLifecycleOwner){list->
            setValuesIntoFragmentViews(list[mPosition])
        }
    }


    @SuppressLint("SetTextI18n")
    private fun setValuesIntoFragmentViews(position: Result) {
        Log.d(TAG, "setValuesIntoFragmentViews: set up the values in fragment")
        mBinding.movieNameView.text = position.title
        mBinding.movieDescView.text = position.overview
        mBinding.movieAvgVoteView.text = position.voteAverage.toString().substring(0, 3)+"⭐"
        mBinding.movieReleaseDateView.text = position.releaseDate
        mBinding.progressBar.visibility = View.VISIBLE

        Picasso.get()
            .load(IMAGE_BASE_URL + position.posterPath)
            .into(mBinding.movieImageView, object : com.squareup.picasso.Callback {
                override fun onSuccess() {
                    mBinding.progressBar.visibility = View.INVISIBLE
                    mBinding.movieImageView.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    mBinding.progressBar.visibility = View.INVISIBLE
                }
            })
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        private const val TAG = "MovieFragment"
    }
}