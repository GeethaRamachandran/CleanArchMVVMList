package com.example.cleanarchmvvmlist.presentation.search_item_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchmvvmlist.common.Resource
import com.example.cleanarchmvvmlist.domain.model.Item
import com.example.cleanarchmvvmlist.domain.use_case.get_list.GetListUseCase
import com.example.cleanarchmvvmlist.domain.use_case.get_search_list.GetSearchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchItemListViewModel @Inject constructor(
    private val getSearchListUseCase: GetSearchListUseCase
) : ViewModel() {

    //creating mutableSateFlow to track state
    private val _stateSearchItemList = MutableStateFlow(SearchItemListState())
    val stateSearchItemList: StateFlow<SearchItemListState> = _stateSearchItemList


    //Get Search list by id
    fun getSearchList(id: Int) {
        getSearchListUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateSearchItemList.value = SearchItemListState(itemList = result.data ?: emptyList())
                    Log.d("Response", "Response" + result.data)
                }
                is Resource.Error -> {
                    _stateSearchItemList.value = SearchItemListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                    Log.d("Error", "Response" + result.message)
                }
                is Resource.Loading -> {
                    _stateSearchItemList.value = SearchItemListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

}