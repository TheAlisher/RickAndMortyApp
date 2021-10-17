package com.alis.rickandmorty.ui.fragments.characters

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.domain.usecases.character.FetchCharacterUseCase
import com.alis.rickandmorty.domain.usecases.character.FetchCharactersUseCase
import com.alis.rickandmorty.domain.usecases.episode.FetchEpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val fetchCharacterUseCase: FetchCharacterUseCase,
    private val fetchEpisodeUseCase: FetchEpisodeUseCase
) : BaseViewModel() {

    fun fetchCharacters() = fetchCharactersUseCase().cachedIn(viewModelScope)

    fun fetchCharacter(id: Int) = fetchCharacterUseCase(id)

    fun fetchEpisode(id: Int) = fetchEpisodeUseCase(id)
}