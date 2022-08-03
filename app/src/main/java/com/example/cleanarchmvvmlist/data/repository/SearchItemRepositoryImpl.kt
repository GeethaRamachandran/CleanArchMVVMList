package com.example.cleanarchmvvmlist.data.repository

import com.example.cleanarchmvvmlist.data.dto.ItemDto
import com.example.cleanarchmvvmlist.data.remote.ItemListApi
import com.example.cleanarchmvvmlist.domain.repository.ItemRepository
import com.example.cleanarchmvvmlist.domain.repository.SearchItemRepository
import javax.inject.Inject

class SearchItemRepositoryImpl @Inject constructor(
    private val api: ItemListApi
): SearchItemRepository {

    override suspend fun getSearchItemList(id: Int): List<ItemDto> {
        return api.getSearchItem(id)
    }
}