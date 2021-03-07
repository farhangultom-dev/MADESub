package com.farhandev.madesub.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
        val publishedAt: String? = null,
        val author: String? = null,
        val urlToImage: String? = null,
        val description: String? = null,
        val title: String? = null,
        val content: String? = null,
        val isFavorite: Boolean
):Parcelable