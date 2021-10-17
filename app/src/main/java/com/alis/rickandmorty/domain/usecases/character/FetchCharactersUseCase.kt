package com.alis.rickandmorty.domain.usecases.character

import com.alis.rickandmorty.domain.repositories.CharacterRepository
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke() = repository.fetchCharacters()
}