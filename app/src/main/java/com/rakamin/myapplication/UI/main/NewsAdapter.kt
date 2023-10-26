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

class NewsAdapter(private val listener: (ArticlesItem) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    // Data berita yang akan ditampilkan dalam RecyclerView
    private var news = listOf<ArticlesItem>()

    // Fungsi untuk mengatur data berita
    fun setNews(news: List<ArticlesItem>) {
        this.news = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Membuat tampilan ViewHolder dan menghubungkannya dengan layout item_news.xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Mengikat data dari item berita ke tampilan ViewHolder
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        // Mengembalikan jumlah total item berita
        return news.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private var tvSource: TextView = itemView.findViewById(R.id.tvSource)
        private var tvDate: TextView = itemView.findViewById(R.id.tvDate)

        // Fungsi untuk mengikat data berita ke tampilan ViewHolder
        fun bind(news: ArticlesItem) {
            tvTitle.text = news.title
            tvSource.text = news.author
            tvDate.text = news.publishedAt

            // Menggunakan Glide untuk memuat gambar berita
            Glide.with(itemView.context)
                .load(news.urlToImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.mandiri))
                .into(itemView.findViewById<ImageView>(R.id.imageView))

            // Menambahkan OnClickListener ke setiap item berita
            itemView.setOnClickListener {
                listener(news)
            }
        }
    }
}
