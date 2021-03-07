package com.farhandev.madesub.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.farhandev.madesub.core.ui.NewsAdapter
import com.farhandev.madesub.detail.DetailNewsActivity
import com.farhandev.madesub.favorite.databinding.ActivityFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteNewsModule)

        supportActionBar?.title = "Favorite News"

        getFavoriteData()
    }

    private fun getFavoriteData() {
        val newsAdapter = NewsAdapter()
        newsAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailNewsActivity::class.java)
            intent.putExtra(DetailNewsActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteNews.observe(this, { data ->
            newsAdapter.setData(data)
        })

        with(binding.rvFavorite){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
    }
}