package com.example.cleanarchmvvmlist.domain.repository

import com.example.cleanarchmvvmlist.data.dto.ItemsDTO

interface ItemSearchRepository {

    suspend fun getItemSearch(userId:Int): ItemsDTO



}