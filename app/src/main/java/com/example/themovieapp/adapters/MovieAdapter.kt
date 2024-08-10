package com.example.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themovieapp.dataModel.Result
import com.example.themovieapp.databinding.MovieViewLayoutBinding
import com.squareup.picasso.Picasso
import java.math.BigDecimal
import java.math.RoundingMode

class MovieAdapter(private val mMovieList : List<Result>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var mBinding : MovieViewLayoutBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        mBinding = MovieViewLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MovieViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setValuesIntoRecyclerView(mMovieList[position])
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }

    class MovieViewHolder(private val mBinding : MovieViewLayoutBinding) : RecyclerView.ViewHolder(mBinding.root){

        @SuppressLint("SetTextI18n")
        fun setValuesIntoRecyclerView(currentItem: Result) {
            mBinding.movieNameView.text = currentItem.title
            mBinding.movieDescView.text = currentItem.overview
            mBinding.movieReleaseDateView.text = currentItem.releaseDate
            BigDecimal(currentItem.voteAverage).setScale(1, RoundingMode.HALF_EVEN).toString()
                .also { mBinding.movieAvgVoteView.text = """$it⭐""" }
            mBinding.movieOriginalLangView.text = currentItem.originalLanguage
            Picasso.get().load(IMAGE_BASE_URL+currentItem.posterPath).into(mBinding.movieImageView)
        }

        companion object {
            private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        }
    }
}
