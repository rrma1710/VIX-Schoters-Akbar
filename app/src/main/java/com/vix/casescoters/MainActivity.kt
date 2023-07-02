package com.vix.casescoters

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.vix.casescoters.databinding.ActivityMainBinding
import com.vix.casescoters.remote.ApiClient
import com.vix.casescoters.response.ArticlesItem
import com.vix.casescoters.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNews()

        iniRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun iniRecycler() {
        binding.rvNews.adapter = NewsAdapter {
            Log.d("MainActivity", "iniRecycler: ${it.title}")
        }
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter {

            moveActivity(it)
        }
    }

    private fun moveActivity(news: ArticlesItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_TITLE, news)
        startActivity(intent)
    }

    private fun getNews() {

        ApiClient.create().getNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("MainActivity", "onResponse: ${response.body().toString()}")
                if (response.isSuccessful) {
                    val articles: List<ArticlesItem> =
                        response.body()?.articles as List<ArticlesItem>
                    if (articles.isNotEmpty()) {
                        newsAdapter.setNews(articles)
                        binding.rvNews.adapter = newsAdapter
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}