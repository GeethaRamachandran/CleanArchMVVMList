package com.example.cleanarchmvvmlist.domain.use_case

import android.util.Log
import com.example.cleanarchmvvmlist.common.Constants.RESPONSE
import com.example.cleanarchmvvmlist.common.Resource
import com.example.cleanarchmvvmlist.data.dto.toItem
import com.example.cleanarchmvvmlist.domain.model.Item
import com.example.cleanarchmvvmlist.domain.repository.ItemListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ItemListUseCase @Inject constructor
    (private val repository: ItemListRepository) {


    operator fun invoke(): Flow<Resource<List<Item>>> = flow {
        try {
            emit(Resource.Loading<List<Item>>())
            val domainData = repository.getItemList().map { it.toItem() }
            /*val domainData =
                if (!data.isEmpty()) data.map { it -> it.toItem() } else emptyList()*/
            Log.d(RESPONSE,"Response: "+domainData.toString()+""+domainData.get(0).body)
            //emit(Resource.Success(data = domainData))
            emit(Resource.Success<List<Item>>(data = domainData))
        } catch (e: HttpException) {
            Log.d(RESPONSE,"Error: "+ e.localizedMessage ?: "An Unknown error occurred")
            emit(Resource.Error<List<Item>>(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            Log.d(RESPONSE,"Error: "+ e.localizedMessage ?: "Check Connectivity")
            emit(Resource.Error<List<Item>>(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {

        }
    }


}