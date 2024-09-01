package com.example.themovieapp.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieapp.dataModel.Result
import com.example.themovieapp.databinding.MovieViewLayoutBinding
import com.example.themovieapp.interfaces.IMovieActions
import com.squareup.picasso.Picasso

class MovieAdapter(private val mMovieList: List<Result>, val mIMovieActions: IMovieActions) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var mBinding: MovieViewLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d(TAG, "onCreateViewHolder: create view holder")
        mBinding =
            MovieViewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, @SuppressLint("RecyclerView") position: Int) {
        Log.d(TAG, "onBindViewHolder: bind the view holder")
        holder.setValuesIntoRecyclerView(mMovieList[position], position)
        holder.setOnClickListener(position)

    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: size of list = ${mMovieList.size}")
        return mMovieList.size
    }

    inner class MovieViewHolder(private val mBinding: MovieViewLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        @SuppressLint("SetTextI18n")
        fun setValuesIntoRecyclerView(currentItem: Result, position: Int) {
            Log.d(TAG, "setValuesIntoRecyclerView: setting values into recycler view")

            // Set the text values
            mBinding.movieNameView.text = currentItem.title
            mBinding.movieDescView.text = currentItem.overview
            mBinding.movieReleaseDateView.text = currentItem.releaseDate
            mBinding.movieAvgVoteView.text = currentItem.voteAverage.toString().substring(0, 3) + "⭐"
            mBinding.movieOriginalLangView.text = currentItem.originalLanguage

            mBinding.progressBar.visibility = View.VISIBLE
            Picasso.get()
                .load(IMAGE_BASE_URL + currentItem.posterPath)
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

        fun setOnClickListener(position: Int) {
            mBinding.movieNameView.setOnClickListener {
                Log.d(TAG, "setOnClickListener: name clicked  $position")
                mIMovieActions.openActivity(position = position)
            }

            mBinding.movieImageView.setOnClickListener {
                Log.d(TAG, "setOnClickListener: image clicked $position")
                mIMovieActions.openActivity(position)
            }
        }
    }


    companion object {
        private const val TAG = "MovieAdapter"
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}
