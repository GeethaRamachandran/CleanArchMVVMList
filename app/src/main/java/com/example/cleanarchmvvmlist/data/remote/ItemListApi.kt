package com.example.cleanarchmvvmlist.data.remote

import com.example.cleanarchmvvmlist.data.dto.ItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemListApi {
    @GET("/posts")
    suspend fun getItemList():List<ItemDto>

    @GET("/posts")
    suspend fun getSearchItem(@Query("userId") id: Int): List<ItemDto>

}