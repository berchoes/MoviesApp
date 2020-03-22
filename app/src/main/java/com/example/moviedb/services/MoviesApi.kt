package com.example.moviedb.services

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.nio.channels.spi.AbstractSelectionKey

interface MoviesApi {

    val apiKey: String

    //?apikey=39a81601&s=Doctor%20Who
    //movie/550?api_key=983b46f95f78ff0f9dd82a7bb2a6d321

    @GET("/movies/popular")
        fun getData(@Query("api_key") apiKey: String): Call<List<MovieModel>>
        //fun getData(): Observable<List<MovieModel>>

}