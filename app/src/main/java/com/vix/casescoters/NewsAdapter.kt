package com.vix.casescoters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vix.casescoters.databinding.ItemNewsBinding
import com.vix.casescoters.response.ArticlesItem

class NewsAdapter(val listener: (ArticlesItem) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news = listOf<ArticlesItem>()

    inner class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener(news[adapterPosition])
            }
        }
    }

    fun setNews(news: List<ArticlesItem>) {
        this.news = news
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = news[position].title

        Glide.with(holder.binding.root)
            .load(news[position].urlToImage)
            .apply(
                RequestOptions().dontTransform()
                    .placeholder(R.drawable.ic_launcher_background)
            )
            .into(holder.binding.ivGambar)
    }
}