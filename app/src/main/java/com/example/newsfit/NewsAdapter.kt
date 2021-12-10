package com.example.newsfit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context, val articles: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ArticleViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_layout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.newsTitle.text=articles[position].title
        holder.newsDescription.text=articles[position].description
        Glide.with(context).load(articles[position].urlToImage).into(holder.newsImage)

        //set onclick Listener
        holder.itemView.setOnClickListener {
            val intent=Intent(context,DetailActivity::class.java)
            intent.putExtra("URL",articles[position].url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var newsTitle=itemView.findViewById<TextView>(R.id.newsTitle)
        var newsDescription=itemView.findViewById<TextView>(R.id.newsDescription)
        var newsImage=itemView.findViewById<ImageView>(R.id.newsImage)

    }

}

