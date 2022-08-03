package com.example.cleanarchmvvmlist.di

import com.example.cleanarchmvvmlist.common.Constants
import com.example.cleanarchmvvmlist.data.remote.ItemListApi
import com.example.cleanarchmvvmlist.data.repository.ItemRepositoryImpl
import com.example.cleanarchmvvmlist.data.repository.SearchItemRepositoryImpl
import com.example.cleanarchmvvmlist.domain.repository.ItemRepository
import com.example.cleanarchmvvmlist.domain.repository.SearchItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideItemListApi(): ItemListApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ItemListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideItemRepository(api: ItemListApi): ItemRepository {
        return ItemRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSearchItemRepository(api: ItemListApi): SearchItemRepository {
        return SearchItemRepositoryImpl(api)
    }
}