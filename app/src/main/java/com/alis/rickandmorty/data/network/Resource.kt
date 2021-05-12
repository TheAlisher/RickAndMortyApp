package com.alis.rickandmorty.data.network

import android.util.Log

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> {
            val result = Resource(status = Status.ERROR, data = data, message = message)
            Log.e("Error resource request:", result.toString())
            return result
        }

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Status.LOADING, data = data, message = null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

sealed class _Resource<out T>(
    val data: T? = null,
    val message: String? = null,
) {
    class Loading<T>(data: T? = null) : _Resource<T>(data = data)
    class Success<T>(data: T) : _Resource<T>(data = data)
    class Error<T>(message: String, data: T? = null) : _Resource<T>(data = data, message = message)
}