package com.farhandev.madesub.core.data.source

import com.farhandev.madesub.core.data.source.remote.network.ApiResponse
import com.farhandev.madesub.core.utils.AppExecutor
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutor: AppExecutor) {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDb().first()
        if (shouldFetch(dbSource)){
            emit(Resource.Loading())
            when(val apiResponse = createCall().first()){
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDb().map {
                        Resource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.Empty ->{
                    emitAll(loadFromDb().map {
                        Resource.Success(
                            it
                        )
                    })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(
                        Resource.Error<ResultType>(
                            apiResponse.errorMessage
                        )
                    )
                }
            }
        }else{
            emitAll(loadFromDb().map {
                Resource.Success(
                    it
                )
            })
        }
    }

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): Flow<ResultType>

    protected open fun onFetchFailed(){}

    protected abstract suspend fun saveCallResult(data: RequestType)

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}