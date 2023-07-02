package com.vix.casescoters.remote

import com.vix.casescoters.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("top-headlines?country=us&apiKey=a8648bf1707d488e8ba10033da64dcfa")
    fun getNews(): Call<NewsResponse>
}