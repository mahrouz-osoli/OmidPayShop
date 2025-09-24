package com.example.omidpayshop.data.Model.Product

import com.google.gson.annotations.SerializedName

data class ProductResponseModel(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    @SerializedName("image")
    val imageUrl: String,
    val rating: RatingResponseModel
)