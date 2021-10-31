package com.alis.rickandmorty.domain.usecases.character

import com.alis.rickandmorty.domain.repositories.CharacterRepository
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(
        name: String? = null,
        status: String? = null,
        species: String? = null,
        type: String? = null,
        gender: String? = null
    ) = repository.fetchCharacters(
        name,
        status,
        species,
        type,
        gender
    )
}