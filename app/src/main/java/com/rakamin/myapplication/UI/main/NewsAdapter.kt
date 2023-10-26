package com.rakamin.myapplication.UI.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rakamin.myapplication.Data.Response.ArticlesItem
import com.rakamin.myapplication.R

class NewsAdapter (private val listener: (ArticlesItem)-> Unit):

    RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

    private var news = listOf<ArticlesItem>()

    fun setNews(news : List<ArticlesItem>){
        this.news = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private var tvSource: TextView = itemView.findViewById(R.id.tvSource)
        private var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        fun bind(news: ArticlesItem){
            tvTitle.text = news.title
            tvSource.text = news.author
            tvDate.text = news.publishedAt

            Glide.with(itemView.context)
                .load(news.urlToImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.mandiri))
                .into(itemView.findViewById<ImageView>(R.id.imageView))

            itemView.setOnClickListener {
                listener(news) }
        }
    }

}
