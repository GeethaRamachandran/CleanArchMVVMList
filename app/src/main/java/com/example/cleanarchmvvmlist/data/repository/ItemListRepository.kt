package com.example.cleanarchmvvmlist.data.repository

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
}