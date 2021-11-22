package com.alis.rickandmorty.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alis.rickandmorty.common.resource.Resource
import com.alis.rickandmorty.presentation.state.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> subscribeTo(
        state: MutableLiveData<UIState<T>>,
        request: () -> Flow<Resource<T>>
    ) {
        viewModelScope.launch {
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
}