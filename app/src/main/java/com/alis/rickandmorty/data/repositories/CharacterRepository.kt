package com.alis.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.alis.rickandmorty.data.network.retrofit.CharacterApiService
import com.alis.rickandmorty.data.repositories.pagingsources.character.CharacterPagingSource
import com.alis.rickandmorty.models.character.Character
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterApiService: CharacterApiService
) {

    fun fetchCharacters(): LiveData<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(characterApiService)
            }
        ).liveData
    }
}