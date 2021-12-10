package com.example.newsfit

import android.graphics.pdf.PdfDocument
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL="https://newsapi.org/"
const val API_KEY="60a2e5cc6a244f33b456c91f441686cd"
//creating interface
interface NewsInterface {
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadLines(@Query("country")country:String,@Query("page")page:Int):Call<News>

    //creating Retrofit object
    object NewsService{
        val newsInstance:NewsInterface
        init{
            val retrofit=Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            newsInstance=retrofit.create(NewsInterface::class.java)
        }
    }

}