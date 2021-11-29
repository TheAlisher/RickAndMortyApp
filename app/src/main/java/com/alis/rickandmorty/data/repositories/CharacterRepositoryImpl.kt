package com.alis.rickandmorty.data.repositories

import com.alis.rickandmorty.common.base.BaseRepository
import com.alis.rickandmorty.data.network.dtos.character.toCharacter
import com.alis.rickandmorty.data.network.apiservices.CharacterApiService
import com.alis.rickandmorty.data.network.pagingsources.CharacterPagingSource
import com.alis.rickandmorty.domain.repositories.CharacterRepository
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject

@BoundTo(CharacterRepository::class)
class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterApiService
) : BaseRepository(), CharacterRepository {

    override fun fetchCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ) = doPagingRequest(
        CharacterPagingSource(service, name, status, species, type, gender)
    )

    override fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id).toCharacter()
    }
}