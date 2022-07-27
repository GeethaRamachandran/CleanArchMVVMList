package com.example.cleanarchmvvmlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchmvvmlist.common.Resource
import com.example.cleanarchmvvmlist.data.dto.Item
import com.example.cleanarchmvvmlist.data.repository.ItemListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: ItemListRepository
) : ViewModel() {
    fun getSearchItems(userId: Int): LiveData<Resource<List<Item>>> {


       return repository.getItem(userId)
    }

    val itemList = repository.getAllList()

}
