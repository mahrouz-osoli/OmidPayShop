package com.example.omidpayshop.data.Repository

import com.example.omidpayshop.data.Service.Product.Interface.IProductService
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import com.example.omidpayshop.data.Dao.ProductDao
import com.example.omidpayshop.data.Model.Entity.ProductEntity

@Singleton
class ProductRepository @Inject constructor(
    private val api: IProductService,
    private val productDao: ProductDao
){
    suspend fun getProducts() = api.getProducts()

    fun getAllProductsFromDb(): Flow<List<ProductEntity>> {
        return productDao.getAllProducts()
    }
    fun getProductByIdFromDb(id: Int): Flow<ProductEntity?>{
        return productDao.getProductById(id)
    }
    suspend fun insertProduct(product: ProductEntity){
        productDao.insertProduct(product)
    }
    suspend fun insertProductList(products: List<ProductEntity>) {
        productDao.insertProducts(products)
    }
}

