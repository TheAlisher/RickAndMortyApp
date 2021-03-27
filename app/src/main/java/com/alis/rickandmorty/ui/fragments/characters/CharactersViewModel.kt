package com.alis.rickandmorty.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.repositories.CharacterRepository
import com.alis.rickandmorty.models.character.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository
) : BaseViewModel() {

    fun fetchCharacters(): LiveData<PagingData<Character>> {
        return repository.fetchCharacters().cachedIn(viewModelScope)
    }
}