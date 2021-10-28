package com.alis.rickandmorty.domain.repositories

import androidx.paging.PagingData
import com.alis.rickandmorty.common.resource.Resource
import com.alis.rickandmorty.domain.models.character.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun fetchCharacters(
        name: String? = null,
        status: String? = null,
        species: String? = null,
        type: String? = null,
        gender: String? = null
    ): Flow<PagingData<Character>>

    fun fetchCharacter(id: Int): Flow<Resource<Character>>
}