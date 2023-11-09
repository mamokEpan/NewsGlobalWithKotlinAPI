package com.rakamin.myapplication.UI.detail

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rakamin.myapplication.Data.Response.ArticlesItem
import com.rakamin.myapplication.R

class DetailActivity : AppCompatActivity() {

    private  lateinit var tvTitle: TextView
    private  lateinit var tvAuthor: TextView
    private  lateinit var tvDescription: TextView
    private  lateinit var ivNews: ImageView



    companion object{
        val EXTRA_NEWS = "extraNews"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ivNews = findViewById(R.id.ivNews)
        ivNews.setImageResource(R.drawable.mandiri)

        val news = intent.getParcelableExtra<ArticlesItem>(EXTRA_NEWS)

        tvTitle = findViewById(R.id.tvTitle)
        tvAuthor = findViewById(R.id.tvAuthor)
        tvDescription = findViewById(R.id.tvDescription)

        Log.d("DetailActivity", news!!.author.toString())
        tvTitle.text = news.title
        tvAuthor.text = news.author
        tvDescription.text = news.description


        Glide.with (this@DetailActivity)
            .load(news.urlToImage)
            .apply(RequestOptions().dontTransform().placeholder(R.drawable.mandiri))
            .into(ivNews)

    }
}