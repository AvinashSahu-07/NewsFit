package com.example.newsfit

import android.graphics.Color
import android.net.DnsResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private var articles= mutableListOf<Article>()
    var pageNum=1
    var totalResults=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter= NewsAdapter(this@MainActivity,articles)
        newsList.adapter=adapter

        //For courasel view Layout manager
        val LayoutManager=StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        LayoutManager.setPagerMode(true)
        LayoutManager.setPagerFlingVelocity(3000)
        LayoutManager.setItemChangedListener(object:StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                //For set color in Background when news changes
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))

                //Checking for in a single page how many news loaded in Logcat
//                Log.d(TAG,"First Item - ${LayoutManager.getFirstVisibleItemPosition()}")
//                Log.d(TAG,"Total count - ${LayoutManager.itemCount}")

                //For jumping to next page in API
                if(totalResults>LayoutManager.itemCount &&
                    LayoutManager.getFirstVisibleItemPosition()>=LayoutManager.itemCount-5){
                    pageNum++
                    getNews()
                }
            }

        })
        newsList.layoutManager=LayoutManager
        getNews()
    }
    private fun getNews(){
        val news= NewsInterface.NewsService.newsInstance.getHeadLines("in",pageNum)
        //We Put Request in Queue in Retrofit which is processed one by one & called the callback
        news.enqueue(object:Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
               val news=response.body()
                if(news!=null){
                    //Log.d("AVINASH",news.toString())
                    totalResults=news.totalResults  //give total results of News which API is showing
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("AVINASH","Error",t)
            }
        })
    }
}