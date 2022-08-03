package com.example.cleanarchmvvmlist.presentation.search_item_list

import com.example.cleanarchmvvmlist.domain.model.Item

data class SearchItemListState(
  val isLoading:Boolean=false,
  val itemList: List<Item> = emptyList(),
  val error:String = ""
)