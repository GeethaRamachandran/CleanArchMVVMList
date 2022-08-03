package com.example.cleanarchmvvmlist.presentation

import com.example.cleanarchmvvmlist.domain.model.Item

data class ItemSearchState(
    val isLoading: Boolean = false,
    val data: List<Item>? = null,
    val error: String = ""
)