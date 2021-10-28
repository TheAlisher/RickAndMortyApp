package com.alis.rickandmorty.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alis.rickandmorty.common.resource.Resource
import com.alis.rickandmorty.presentation.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

abstract class BaseViewModel : ViewModel() {

    protected suspend fun <T> subscribeTo(
        state: MutableLiveData<UIState<T>>,
        request: () -> Flow<Resource<T>>
    ) {
        request().collect {
            when (it) {
                is Resource.Loading -> {
                    state.value = UIState.Loading()
                }
                is Resource.Error -> it.message?.let { error ->
                    state.value = UIState.Error(error)
                }

                is Resource.Success -> it.data?.let { data ->
                    state.value = UIState.Success(data)
                }
            }
        }
    }
}