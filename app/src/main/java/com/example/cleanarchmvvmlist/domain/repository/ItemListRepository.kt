package com.example.cleanarchmvvmlist.domain.repository

import com.example.cleanarchmvvmlist.data.dto.ItemDto
import com.example.cleanarchmvvmlist.data.dto.ItemsDTO
import retrofit2.Response

interface ItemListRepository {

    suspend fun getItemList(): List<ItemDto>

}