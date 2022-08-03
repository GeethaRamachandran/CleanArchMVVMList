package com.example.cleanarchmvvmlist.presentation.item_list

import com.example.cleanarchmvvmlist.domain.model.Item

data class ItemListState(
  val isLoading:Boolean=false,
  val itemList: List<Item> = emptyList(),
  val error:String = ""
)