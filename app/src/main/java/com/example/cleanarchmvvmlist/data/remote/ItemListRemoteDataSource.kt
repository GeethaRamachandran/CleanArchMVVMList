package com.example.cleanarchmvvmlist.data.remote

import javax.inject.Inject

class ItemListRemoteDataSource @Inject constructor(
    private val itemListService: ItemListService
): BaseDataSource() {
    suspend fun getAllItem() = getResult { itemListService.getAllItem() }
    suspend fun getSearchItem(id: Int) = getResult { itemListService.getSearchItem(id) }
}