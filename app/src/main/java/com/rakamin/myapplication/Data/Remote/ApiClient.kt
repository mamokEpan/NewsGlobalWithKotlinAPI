package com.rakamin.myapplication.Data.Remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    // URL dasar dari API yang akan diakses
    private const val BASE_URL = "https://newsapi.org/v2/"

    // Fungsi untuk membuat instance Retrofit dan mengonfigurasinya
    fun create(): ApiServices {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Menggunakan Gson untuk mengonversi respons JSON
            .client(okHttpClient()) // Menggunakan OkHttpClient untuk manajemen permintaan HTTP
            .build()
        return retrofit.create(ApiServices::class.java)
    }

    // Fungsi untuk konfigurasi OkHttpClient
    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY) // Interceptor untuk mencatat log permintaan dan respons HTTP
            )
            .build()
    }
}
