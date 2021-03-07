package com.farhandev.madesub.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farhandev.madesub.core.R
import com.farhandev.madesub.core.databinding.ItemNewsBinding
import com.farhandev.madesub.core.domain.model.News

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {
    private var listData = ArrayList<News>()
    var onItemClick: ((News) -> Unit)? = null

    fun setData(newListData: List<News>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context). inflate(R.layout.item_news, parent, false))

    override fun onBindViewHolder(holder: NewsAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = ItemNewsBinding.bind(itemView)
        fun bind(data: News){
            with(binding){
                Glide.with(itemView.context)
                    .load(data.urlToImage)
                    .into(ivPoster)
                tvTitle.text = data.title
                tvDesc.text = data.description
                tvTgl.text = data.publishedAt
            }
        }

        init {
            binding.root.setOnClickListener{
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}