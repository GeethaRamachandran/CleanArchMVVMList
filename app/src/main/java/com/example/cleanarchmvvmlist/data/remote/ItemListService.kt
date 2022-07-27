package com.example.cleanarchmvvmlist.data.remote

import com.example.cleanarchmvvmlist.data.dto.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemListService {
    @GET("/posts")
    suspend fun getAllItem():Response<List<Item>>

    @GET("/posts")
    suspend fun getSearchItem(@Query("userId") id: Int): Response<List<Item>>
}