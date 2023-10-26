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

    private lateinit var rvNews: RecyclerView
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Memanggil fungsi untuk mendapatkan data berita
        getNews()

        // Menginisialisasi RecyclerView
        rvNews = findViewById(R.id.rvNews)

        // Menginisialisasi dan mengatur RecyclerView
        initRecyclerView()
    }

    private fun initRecyclerView() {
        // Membuat adapter untuk RecyclerView
        adapter = NewsAdapter {
            moveActivity(it)
        }

        // Mengatur tampilan layout RecyclerView menjadi linear (vertikal)
        rvNews.layoutManager = LinearLayoutManager(this)

        // Menetapkan adapter ke RecyclerView
        rvNews.adapter = adapter
    }

    private fun moveActivity(news: ArticlesItem) {
        // Fungsi ini dapat digunakan untuk menavigasi ke halaman detail berita
        // Namun, di dalam kode ini, fungsinya belum diimplementasikan.
    }

    private fun getNews() {
        // Menggunakan Retrofit untuk mengambil data berita dari API
        ApiClient.create().getNews().enqueue(object : Callback<NewsResponse> {

            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    // Jika permintaan berhasil, kita mendapatkan daftar berita dan mengirimkannya ke adapter
                    val articles: List<ArticlesItem> = response.body()?.articles as List<ArticlesItem>
                    adapter.setNews(articles)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                // Jika permintaan gagal, tindakan yang sesuai dapat diambil di sini
                // Misalnya, menampilkan pesan kesalahan kepada pengguna atau mencoba permintaan ulang.
            }
        })
    }
}
