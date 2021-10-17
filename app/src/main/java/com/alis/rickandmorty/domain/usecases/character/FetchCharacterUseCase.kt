package com.alis.rickandmorty.domain.usecases.character

import com.alis.rickandmorty.domain.repositories.CharacterRepository
import javax.inject.Inject

class FetchCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int) = repository.fetchCharacter(id)
}