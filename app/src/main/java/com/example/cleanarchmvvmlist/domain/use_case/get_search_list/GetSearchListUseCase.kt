package com.example.cleanarchmvvmlist.domain.use_case.get_search_list

import com.example.cleanarchmvvmlist.common.Resource
import com.example.cleanarchmvvmlist.data.dto.toItem
import com.example.cleanarchmvvmlist.domain.model.Item
import com.example.cleanarchmvvmlist.domain.repository.ItemRepository
import com.example.cleanarchmvvmlist.domain.repository.SearchItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSearchListUseCase @Inject constructor(
    private val repository: SearchItemRepository
) {

    operator fun invoke(id:Int):Flow<Resource<List<Item>>>  = flow {
        try {
            emit(Resource.Loading<List<Item>>())
            val items = repository.getSearchItemList(id).map { it.toItem() }
            emit(Resource.Success<List<Item>>(items))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Item>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Item>>("Couldn't reach server. Check your internet connection."))
        }
    }
}