package com.example.themovieapp.dataModel

import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("id")
    val id: Int,

    @SerializedName("original_language")
    val originalLanguage: String, //movie original language

    @SerializedName("overview")
    val overview: String, //desc

    @SerializedName("poster_path")
    val posterPath: String, //image

    @SerializedName("release_date")
    val releaseDate: String, //release date of the movie

    @SerializedName("title")
    val title: String, // movie name

    @SerializedName("vote_average")
    val voteAverage: Double, //average vote of the movie

)