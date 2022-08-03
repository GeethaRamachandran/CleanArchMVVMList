package com.example.cleanarchmvvmlist.data.repository

import com.example.cleanarchmvvmlist.data.dto.ItemDto
import com.example.cleanarchmvvmlist.data.remote.ItemListApi
import com.example.cleanarchmvvmlist.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val api: ItemListApi
): ItemRepository {
    override suspend fun getItemList(): List<ItemDto> {

        return api.getItemList()
    }
}