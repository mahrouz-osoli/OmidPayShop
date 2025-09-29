package com.example.omidpayshop.data.Service.Product.Interface

import com.example.omidpayshop.data.Model.Product.ProductResponseModel
import retrofit2.http.GET

interface IProductService {
    @GET("products")
    suspend fun getProducts(): List<ProductResponseModel>
}