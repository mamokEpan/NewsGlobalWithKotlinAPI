package com.rakamin.myapplication.Data.Remote

import com.rakamin.myapplication.Data.Response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("https://newsapi.org/v2/everything?domains=wsj.com&apiKey=5e947beafeb0455b91ac10f7f843ec62")
    fun getNews(): Call<NewsResponse>
}