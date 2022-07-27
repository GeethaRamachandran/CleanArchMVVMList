package com.example.cleanarchmvvmlist.di

import com.example.cleanarchmvvmlist.common.Constants
import com.example.cleanarchmvvmlist.data.remote.ItemListRemoteDataSource
import com.example.cleanarchmvvmlist.data.remote.ItemListService
import com.example.cleanarchmvvmlist.data.remote.NullOnEmptyConverterFactory
import com.example.cleanarchmvvmlist.data.repository.ItemListRepository
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


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideItemListService(retrofit: Retrofit): ItemListService = retrofit.create(ItemListService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(itemListService: ItemListService) = ItemListRemoteDataSource(itemListService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ItemListRemoteDataSource
    ) =
        ItemListRepository(remoteDataSource)
}