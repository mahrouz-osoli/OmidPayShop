package com.example.omidpayshop.di.DatabaseModule

import android.content.Context
import androidx.room.Room
import com.example.RoomDatabase
import com.example.omidpayshop.data.Dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): RoomDatabase {
        return Room.databaseBuilder(
            appContext,
            RoomDatabase::class.java,
            "omidpay_db"
        ).build()
    }

    @Provides
    fun provideProductDao(database: RoomDatabase): ProductDao {
        return database.ProductDao()
    }
}