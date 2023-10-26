package com.rakamin.myapplication.Data

data class News(
    val id: String?,             // ID unik dari berita (opsional)
    val name: String?,           // Nama sumber berita
    val author: String?,         // Nama penulis berita
    val title: String?,          // Judul berita
    val urlToImage: String?,     // URL gambar yang terkait dengan berita
    val publishedAt: String?     // Tanggal publikasi berita
)
