package com.alis.rickandmorty.common.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alis.rickandmorty.common.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (ioException: Exception) {
            emit(
                Resource.Error(
                    data = null, message = ioException.localizedMessage ?: "Error Occurred!"
                )
            )
        }
    }

    protected fun <ValueDto : Any, Value : Any> doPagingRequest(
        pagingSource: BasePagingSource<ValueDto, Value>,
        pageSize: Int = 10,
        prefetchDistance: Int = pageSize,
        enablePlaceholders: Boolean = true,
        initialLoadSize: Int = pageSize * 3,
        maxSize: Int = Int.MAX_VALUE,
        jumpThreshold: Int = Int.MIN_VALUE
    ): Flow<PagingData<Value>> {
        return Pager(
            config = PagingConfig(
                pageSize,
                prefetchDistance,
                enablePlaceholders,
                initialLoadSize,
                maxSize,
                jumpThreshold
            ),
            pagingSourceFactory = {
                pagingSource
            },
        ).flow
    }
}