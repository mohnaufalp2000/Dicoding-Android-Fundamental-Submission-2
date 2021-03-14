package com.naufal.aplikasigithubuser2.view.model

import com.google.gson.annotations.SerializedName

data class User(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("incomplete_results")
	val incompleteResults: Boolean? = null,

	@field:SerializedName("items")
	val items: ArrayList<ItemsItem?>? = null
)


