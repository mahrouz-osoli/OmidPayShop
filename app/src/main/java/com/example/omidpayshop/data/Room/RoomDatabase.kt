package com.example

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.omidpayshop.data.Dao.ProductDao
import com.example.omidpayshop.data.Model.Entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class RoomDatabase : RoomDatabase(){
    abstract fun ProductDao(): ProductDao
}