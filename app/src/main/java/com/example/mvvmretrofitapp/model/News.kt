package com.example.mvvmretrofitapp.model


import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("category")
    val category: String?,
    @SerializedName("data")
    val `data`: MutableList<Data?>?,
    @SerializedName("success")
    val success: Boolean?
)