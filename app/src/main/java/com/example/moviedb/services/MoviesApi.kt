package com.example.moviedb.services

import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {
//apikey=39a81601

    @GET("?apikey=39a81601&s=Doctor%20Who")
        fun getData(): Call<List<MovieModel>>

}