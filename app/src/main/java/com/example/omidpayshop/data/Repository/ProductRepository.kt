package com.example.omidpayshop.data.Repository

import com.example.omidpayshop.data.Service.Product.Interface.IProductService
import javax.inject.Inject


class ProductRepository @Inject constructor(
    private val api: IProductService
){
    suspend fun getProducts() = api.getProducts()
}

