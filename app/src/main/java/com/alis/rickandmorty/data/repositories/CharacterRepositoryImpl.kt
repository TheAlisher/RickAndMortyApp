package com.alis.rickandmorty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alis.rickandmorty.common.base.BaseRepository
import com.alis.rickandmorty.data.network.dtos.character.toCharacter
import com.alis.rickandmorty.data.network.apiservices.CharacterApiService
import com.alis.rickandmorty.data.network.pagingsources.CharacterPagingSource
import com.alis.rickandmorty.domain.repositories.CharacterRepository
import com.alis.rickandmorty.domain.models.character.Character
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterApiService
) : BaseRepository(), CharacterRepository {

    override fun fetchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(service, name, status, species, type, gender)
            }
        ).flow
    }

    override fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id).toCharacter()
    }
}