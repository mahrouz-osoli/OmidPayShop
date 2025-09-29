package com.example.omidpayshop.di.NetworkModule

import com.example.omidpayshop.data.Service.Product.Interface.IProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    private const val BASE_URL = "https://fakestoreapi.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): IProductService{
        return retrofit.create(IProductService::class.java)
    }
}