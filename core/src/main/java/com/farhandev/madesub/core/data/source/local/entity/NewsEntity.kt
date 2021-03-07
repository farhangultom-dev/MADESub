package com.farhandev.madesub.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "publishedAt")
        var publishedAt: String,

        @ColumnInfo(name = "author")
        var author: String? = null,

        @ColumnInfo(name = "urlToImage")
        var urlToImage: String? = null,

        @ColumnInfo(name = "description")
        var description: String? = null,

        @ColumnInfo(name = "title")
        var title: String? = null,

        @ColumnInfo(name = "content")
        var content: String? = null,

        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
)