package com.example.cleanarchmvvmlist.domain.use_case

import com.example.cleanarchmvvmlist.common.Resource
import com.example.cleanarchmvvmlist.domain.model.Item
import com.example.cleanarchmvvmlist.domain.repository.ItemSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchItemUseCase @Inject constructor(private val repository: ItemSearchRepository) {


    operator fun invoke(q: Int): Flow<Resource<List<Item>>> = flow {
        try {
            //emit(Resource.Loading<List<Item>>())
            val data = repository.getItemSearch(q)
            val domainData =
                if (data.items != null) data.items.map { it -> it } else emptyList()
            emit(Resource.Success(data = domainData))
        } catch (e: HttpException) {
           // emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            //emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }


}