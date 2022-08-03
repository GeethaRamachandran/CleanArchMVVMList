package com.example.cleanarchmvvmlist.di

import com.example.cleanarchmvvmlist.common.Constants

import com.example.cleanarchmvvmlist.data.remote.ItemListService
import com.example.cleanarchmvvmlist.data.repository.ItemListRepositoryImpl
import com.example.cleanarchmvvmlist.data.repository.ItemSearchRepositoryImpl
import com.example.cleanarchmvvmlist.domain.repository.ItemListRepository
import com.example.cleanarchmvvmlist.domain.repository.ItemSearchRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
    fun provideItemListService(): ItemListService {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ItemListService::class.java)
    }





/*    @Singleton
    @Provides
    fun provideRemoteDataSource(itemListService: ItemListService) = ItemListRemoteDataSource(itemListService)*/

    @Singleton
    @Provides
    fun provideItemListRepository(itemListService: ItemListService): ItemListRepository {
        return ItemListRepositoryImpl(itemListService)
    }

    @Singleton
    @Provides
    fun provideItemSearchRepository(itemListService: ItemListService): ItemSearchRepository {
        return ItemSearchRepositoryImpl(itemListService)
    }

  /*  @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ItemListRemoteDataSource
    ) =
        ItemListRepository(remoteDataSource)*/
}