package com.example.cleanarchmvvmlist.data.repository

import com.example.cleanarchmvvmlist.data.dto.ItemsDTO
import com.example.cleanarchmvvmlist.data.remote.ItemListService
import com.example.cleanarchmvvmlist.domain.repository.ItemSearchRepository

class ItemSearchRepositoryImpl(private val itemListService: ItemListService) : ItemSearchRepository {

    override suspend fun getItemSearch(userId: Int): ItemsDTO {
        return itemListService.getSearchItem(userId)
    }
}