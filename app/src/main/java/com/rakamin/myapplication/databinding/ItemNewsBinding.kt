package com.rakamin.myapplication.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rakamin.myapplication.Data.News
import com.rakamin.myapplication.R

class ItemNewsBinding(
    val root: View
) {
    val imageView: ImageView = root.findViewById(R.id.imageView)
    val tvTitle: TextView = root.findViewById(R.id.tvTitle)
    val tvSource: TextView = root.findViewById(R.id.tvSource)
    val tvDate: TextView = root.findViewById(R.id.tvDate)

    fun bind(news: News) {
        // Mengisi data ke tampilan
        tvTitle.text = news.title
        tvSource.text = news.author
        tvDate.text = news.publishedAt

        Glide.with(root.context)
            .load(news.urlToImage) // Gantilah 'imageUrl' dengan atribut yang sesuai dalam model 'News'
            .into(imageView)
    }

    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup): ItemNewsBinding {
            val view = inflater.inflate(R.layout.item_news, parent, false)
            return ItemNewsBinding(view)
        }
    }
}
