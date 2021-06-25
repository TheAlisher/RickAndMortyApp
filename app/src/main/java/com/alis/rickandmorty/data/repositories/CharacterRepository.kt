package com.alis.rickandmorty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alis.rickandmorty.data.network.retrofit.apiservices.CharacterApiService
import com.alis.rickandmorty.data.repositories.pagingsources.CharacterPagingSource
import com.alis.rickandmorty.models.character.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterApiService: CharacterApiService
) {

    fun fetchCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(characterApiService)
            }
        ).flow
    }
}