package com.alis.rickandmorty.presentation.ui.fragments.characters

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.common.base.BaseViewModel
import com.alis.rickandmorty.domain.usecases.character.FetchCharactersUseCase
import com.alis.rickandmorty.domain.usecases.episode.FetchEpisodeUseCase
import com.alis.rickandmorty.domain.usecases.location.FetchLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val fetchLocationUseCase: FetchLocationUseCase,
    private val fetchEpisodeUseCase: FetchEpisodeUseCase,
) : BaseViewModel() {

    fun fetchCharacters() = fetchCharactersUseCase().cachedIn(viewModelScope)

    fun fetchLocation(id: Int) = fetchLocationUseCase(id)

    fun fetchEpisode(id: Int) = fetchEpisodeUseCase(id)
}