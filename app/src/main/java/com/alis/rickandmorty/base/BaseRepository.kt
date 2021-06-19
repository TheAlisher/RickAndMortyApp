package com.alis.rickandmorty.base

import com.alis.rickandmorty.data.network._Resource
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(_Resource.Loading())
        try {
            emit(_Resource.Success(data = request()))
        } catch (ioException: Exception) {
            emit(
                _Resource.Error(
                    data = null, message = ioException.localizedMessage ?: "Error Occurred!"
                )
            )
        }
    }
}