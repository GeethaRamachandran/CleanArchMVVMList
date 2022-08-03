package com.example.cleanarchmvvmlist.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchmvvmlist.common.Constants
import com.example.cleanarchmvvmlist.common.Resource
import com.example.cleanarchmvvmlist.domain.use_case.ItemListUseCase
import com.example.cleanarchmvvmlist.domain.use_case.SearchItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/*@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: ItemListRepository
) : ViewModel() {
    fun getSearchItems(userId: Int): LiveData<Resource<List<ItemDto>>> {


       return repository.getItem(userId)
    }

    val itemList = repository.getAllList()

}*/

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    /*private val searchItemUseCase: SearchItemUseCase,*/
    private val itemListUseCase: ItemListUseCase
) : ViewModel() {


    init {
        getItemList()
    }
    private val _itemList = MutableStateFlow<ItemListState>(ItemListState())
    val itemList: StateFlow<ItemListState> = _itemList

    private val _itemSearchList = MutableStateFlow<ItemSearchState>(ItemSearchState())
    val itemSearchList: StateFlow<ItemSearchState> = _itemSearchList

    fun getItemList() {
        itemListUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _itemList.value = ItemListState(isLoading = true)
                }
                is Resource.Success -> {
                    ItemListState(itemList = it.data ?: emptyList())
                  //  _itemList.value = ItemListState(itemList = it.data!!)
             +       Log.d(Constants.RESPONSE,"Response viewmodel"+it.toString())
                    Log.d("Response","Response"+it.data)

                }
                is Resource.Error -> {
                    _itemList.value = ItemListState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }


   /* fun getSearchItems(s: Int) {
        searchItemUseCase(s).onEach {
            when (it) {
                is Resource.Loading -> {
                    _itemSearchList.value = ItemSearchState(isLoading = true)
                }
                is Resource.Success -> {
                    _itemSearchList.value = ItemSearchState(data = it.data)
                }
                is Resource.Error -> {
                    _itemSearchList.value = ItemSearchState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }*/
}