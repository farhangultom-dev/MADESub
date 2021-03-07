package com.farhandev.madesub.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int,

	@field:SerializedName("articles")
	val articles: List<NewsResponse>,

	@field:SerializedName("status")
	val status: String
)
