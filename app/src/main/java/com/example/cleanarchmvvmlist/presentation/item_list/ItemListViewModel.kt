package com.example.cleanarchmvvmlist.presentation.item_list

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
class ItemListViewModel @Inject constructor(
    private val getListUseCase: GetListUseCase,
    private val getSearchListUseCase: GetSearchListUseCase
) : ViewModel() {

    //creating mutableSateFlow to track state
    private val _state = MutableStateFlow(ItemListState())
    val state: StateFlow<ItemListState> = _state


    init {
        getList()
    }

    //Get All list
     fun getList() {
        getListUseCase().onEach { result ->
            getResult(result)
        }.launchIn(viewModelScope)
    }

    //Get Search list by id
    fun getSearchList(id: Int) {
        getSearchListUseCase(id).onEach { result ->
            getResult(result)

        }.launchIn(viewModelScope)
    }

    //Using same method since we have same list to render
    private fun getResult(result: Resource<List<Item>>) {

        when (result) {
            is Resource.Success -> {
                _state.value = ItemListState(itemList = result.data ?: emptyList())
                Log.d("Response", "Response" + result.data)
            }
            is Resource.Error -> {
                _state.value = ItemListState(
                    error = result.message ?: "An unexpected error occured"
                )
                Log.d("Error", "Response" + result.message)
            }
            is Resource.Loading -> {
                _state.value = ItemListState(isLoading = true)
            }
        }
    }
}