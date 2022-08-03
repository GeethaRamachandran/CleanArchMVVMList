package com.example.cleanarchmvvmlist.data.remote

import com.example.cleanarchmvvmlist.data.dto.ItemDto
import com.example.cleanarchmvvmlist.data.dto.ItemsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemListService {
    @GET("/posts")
    suspend fun getAllItem():List<ItemDto>

    @GET("/posts")
    suspend fun getSearchItem(@Query("userId") id: Int): ItemsDTO
}