package com.alis.rickandmorty.data.network.pagingsources

import com.alis.rickandmorty.base.BasePagingSource
import com.alis.rickandmorty.data.network.apiservices.CharacterApiService
import com.alis.rickandmorty.data.network.dtos.character.CharacterDto
import com.alis.rickandmorty.data.network.dtos.character.toCharacter
import com.alis.rickandmorty.domain.models.character.Character

class CharacterPagingSource(
    private val service: CharacterApiService,
    private val name: String?,
    private val status: String?,
    private val species: String?,
    private val type: String?,
    private val gender: String?,
) : BasePagingSource<CharacterDto, Character>(
    { service.fetchCharacters(it, name, status, species, type, gender) },
    { data -> data.map { it.toCharacter() } }
)