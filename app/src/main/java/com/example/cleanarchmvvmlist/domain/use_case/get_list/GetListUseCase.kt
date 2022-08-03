package com.example.cleanarchmvvmlist.domain.use_case.get_list

import com.example.cleanarchmvvmlist.common.Resource
import com.example.cleanarchmvvmlist.data.dto.toItem
import com.example.cleanarchmvvmlist.domain.model.Item
import com.example.cleanarchmvvmlist.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetListUseCase @Inject constructor(
    private val repository: ItemRepository
) {

    operator fun invoke():Flow<Resource<List<Item>>>  = flow {
        try {
            emit(Resource.Loading<List<Item>>())
            val items = repository.getItemList().map { it.toItem() }
            emit(Resource.Success<List<Item>>(items))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Item>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Item>>("Couldn't reach server. Check your internet connection."))
        }
    }
}