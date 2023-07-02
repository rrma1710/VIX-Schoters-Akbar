package com.vix.casescoters

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vix.casescoters.databinding.ActivityDetailBinding
import com.vix.casescoters.response.ArticlesItem

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_TITLE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater) // inflate layout
        setContentView(binding.root) // set layout

        val news = intent.getParcelableExtra<ArticlesItem>(EXTRA_TITLE)

        news?.let { initDetail(it) }

    }

    private fun initDetail(news: ArticlesItem?) {
        binding.tvTitleDetail.text = news!!.title
        binding.tvAuthor.text = news.author
        binding.tvDeskripsiDetail.text = news.description
        Glide.with(this)
            .load(news.urlToImage)
            .apply(
                RequestOptions().dontTransform()
                    .placeholder(R.drawable.sample)
            )
            .into(binding.ivImgDetail)

        binding.btnToNews.setOnClickListener {
            openWeb(news.url)
        }

    }

    private fun openWeb(url: String?) {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}