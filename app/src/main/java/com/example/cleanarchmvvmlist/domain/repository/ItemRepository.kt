package com.example.cleanarchmvvmlist.domain.repository

import com.example.cleanarchmvvmlist.data.dto.ItemDto

interface ItemRepository {

    suspend fun getItemList(): List<ItemDto>
}