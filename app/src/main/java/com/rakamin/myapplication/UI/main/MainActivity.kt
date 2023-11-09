package com.rakamin.myapplication.UI.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rakamin.myapplication.Data.Remote.ApiClient
import com.rakamin.myapplication.Data.Response.ArticlesItem
import com.rakamin.myapplication.Data.Response.NewsResponse
import com.rakamin.myapplication.R
import com.rakamin.myapplication.UI.detail.DetailActivity
import com.rakamin.myapplication.UI.detail.DetailActivity.Companion.EXTRA_NEWS
import com.rakamin.myapplication.activity_about
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var rvNews: RecyclerView
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aboutPage = findViewById<ImageView>(R.id.about_page)
        aboutPage.setOnClickListener {
            val intent = Intent(this, activity_about::class.java)
            startActivity(intent)
        }

//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)

//        toolbar.setOnMenuItemClickListener{
//            item ->
//            if (item.itemId == R.id.action_about){
//                return@setOnMenuItemClickListener true
//            }
//            false
//        }

                // Memanggil fungsi untuk mendapatkan data berita
        getNews()

        // Menginisialisasi RecyclerView
        rvNews = findViewById(R.id.rvNews)

        // Menginisialisasi dan mengatur RecyclerView
        initRecyclerView()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.action_about ->{
                val intent = Intent(this, activity_about::class.java)
                startActivity(intent)
                Log.d("AboutActivity", "AboutActivity onCreate called")
                return true

            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
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
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(EXTRA_NEWS, news)
        startActivity(intent)
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
