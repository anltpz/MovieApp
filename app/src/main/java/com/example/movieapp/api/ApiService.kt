package com.example.movieapp.api

import com.example.movieapp.helper.Constants
import com.example.movieapp.model.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getTvShows():Response<TvShowResponse>
}