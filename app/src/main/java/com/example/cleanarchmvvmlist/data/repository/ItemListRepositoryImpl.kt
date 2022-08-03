package com.example.cleanarchmvvmlist.data.repository

import com.example.cleanarchmvvmlist.data.dto.ItemDto
import com.example.cleanarchmvvmlist.data.dto.ItemsDTO
import com.example.cleanarchmvvmlist.data.remote.ItemListService
import com.example.cleanarchmvvmlist.domain.repository.ItemListRepository
import retrofit2.Response
import javax.inject.Inject


class ItemListRepositoryImpl @Inject constructor
    (private val itemListService: ItemListService) : ItemListRepository {

    override suspend fun getItemList(): List<ItemDto> {
        return itemListService.getAllItem()
    }

}
/*
import com.example.cleanarchmvvmlist.common.performGetOperation
import com.example.cleanarchmvvmlist.data.remote.ItemListRemoteDataSource

import javax.inject.Inject

class ItemListRepository @Inject constructor(
    private val remoteDataSource: ItemListRemoteDataSource
) {
    fun getAllList() = performGetOperation(
        networkCall = { remoteDataSource.getAllItem() }
    )
    fun getItem(id: Int) = performGetOperation(
        networkCall = { remoteDataSource.getSearchItem(id) }
    )
}*/
