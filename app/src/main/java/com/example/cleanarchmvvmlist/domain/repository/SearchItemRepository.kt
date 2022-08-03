package com.example.cleanarchmvvmlist.domain.repository

import com.example.cleanarchmvvmlist.data.dto.ItemDto

interface SearchItemRepository {

    suspend fun getSearchItemList(id:Int): List<ItemDto>
}