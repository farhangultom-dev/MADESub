package com.farhandev.madesub.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.farhandev.madesub.R
import com.farhandev.madesub.core.domain.model.News
import com.farhandev.madesub.databinding.ActivityDetailNewsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailNewsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailNewsViewModel: DetailNewsViewModel by viewModel()
    private lateinit var binding: ActivityDetailNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailNews = intent.getParcelableExtra<News>(EXTRA_DATA)
        showDetailNews(detailNews)
    }

    private fun showDetailNews(detailNews: News?) {
        detailNews?.let {
            supportActionBar?.title = getString(R.string.app_name)
            binding.content.contentDetail.text = detailNews.description
            binding.content.authorDetail.text = detailNews.author
            binding.content.titleDetail.text = detailNews.title
            Glide.with(this@DetailNewsActivity)
                    .load(detailNews.urlToImage)
                    .into(binding.ivImageDetail)

            var statusFavorite = detailNews.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailNewsViewModel.setFavorrite(detailNews, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite){
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        }else{
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_false_favorite))
        }
    }
}