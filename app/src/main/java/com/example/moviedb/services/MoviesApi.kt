package com.example.moviedb.services

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MoviesApi {

    //?apikey=39a81601&s=Doctor%20Who

    @GET("/")
        fun getData(@Query("apikey") apiKey: String, @Query("s") search : String): Call<ResponseModel>
        //fun getData(): Observable<List<MovieModel>>

}