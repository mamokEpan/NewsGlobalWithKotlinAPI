package com.rakamin.myapplication.UI.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rakamin.myapplication.Data.Remote.ApiClient
import com.rakamin.myapplication.Data.Response.ArticlesItem
import com.rakamin.myapplication.Data.Response.NewsResponse
import com.rakamin.myapplication.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var rvNews:RecyclerView
    private lateinit var adapter: NewsAdapter
    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
        rvNews = findViewById(R.id.rvNews)
        initRecyclerView()
    }

    private fun initRecyclerView() {

        adapter = NewsAdapter {
            moveActivity(it)
        }
        rvNews.layoutManager = LinearLayoutManager(this)

        rvNews.adapter = adapter
    }

    private fun moveActivity(news: ArticlesItem) {

    }

    private fun getNews() {

        ApiClient.create().getNews().enqueue(object : Callback<NewsResponse> {

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful){
                    val articles: List <ArticlesItem> =
                        response.body()?.articles as List<ArticlesItem>
                    adapter.setNews(articles)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

        })
    }
}