package com.alis.rickandmorty.ui.fragments.characters

import androidx.lifecycle.LiveData
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.network.Resource
import com.alis.rickandmorty.data.repositories.RickAndMortyRepository
import com.alis.rickandmorty.models.character.Character
import com.alis.rickandmorty.models.common.RickAndMortyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : BaseViewModel() {

    fun fetchCharacters(): LiveData<Resource<Response<RickAndMortyResponse<Character>>>> {
        return repository.fetchCharacters()
    }
}