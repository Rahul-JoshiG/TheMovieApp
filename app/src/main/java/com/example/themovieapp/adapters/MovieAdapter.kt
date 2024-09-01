package com.example.themovieapp.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieapp.dataModel.Result
import com.example.themovieapp.databinding.MovieViewLayoutBinding
import com.example.themovieapp.interfaces.IMovieActions
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.properties.Delegates

class MovieAdapter(private val mMovieList: List<Result>, val mIMovieActions: IMovieActions) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var mBinding: MovieViewLayoutBinding
    private var mCurrentPosition by Delegates.notNull<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d(TAG, "onCreateViewHolder: create view holder")
        mBinding =
            MovieViewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: bind the view holder")
        mCurrentPosition = position
        holder.setValuesIntoRecyclerView(mMovieList[position])
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: size of list = ${mMovieList.size}")
        return mMovieList.size
    }

    inner class MovieViewHolder(private val mBinding: MovieViewLayoutBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        @SuppressLint("SetTextI18n")
        fun setValuesIntoRecyclerView(currentItem: Result) {
            Log.d(TAG, "setValuesIntoRecyclerView: setting values into recycler view")
            mBinding.movieNameView.text = currentItem.title
            mBinding.movieDescView.text = currentItem.overview
            mBinding.movieReleaseDateView.text = currentItem.releaseDate
            BigDecimal(currentItem.voteAverage).setScale(1, RoundingMode.HALF_EVEN).toString()
                .also { mBinding.movieAvgVoteView.text = """$it⭐""" }
            mBinding.movieOriginalLangView.text = currentItem.originalLanguage
            Picasso.get().load(IMAGE_BASE_URL + currentItem.posterPath)
                .into(mBinding.movieImageView)


            setOnClickListener()
        }

        private fun setOnClickListener() {
            mBinding.movieNameView.setOnClickListener{
                Log.d(TAG, "setOnClickListener: going to main activity with position of $mCurrentPosition")
                mIMovieActions.openActivity(position = mCurrentPosition)
            }
        }

    }

    companion object {
        private const val TAG = "MovieAdapter"
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }
}
