package com.alis.rickandmorty.presentation.ui.fragments.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.common.base.BaseViewModel
import com.alis.rickandmorty.domain.usecases.character.FetchCharactersUseCase
import com.alis.rickandmorty.domain.usecases.episode.FetchEpisodesUseCase
import com.alis.rickandmorty.domain.usecases.location.FetchLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val fetchLocationsUseCase: FetchLocationsUseCase,
    private val fetchEpisodesUseCase: FetchEpisodesUseCase
) : BaseViewModel() {

    fun fetchCharacters(
        name: String
    ) = fetchCharactersUseCase(
        name = name
    ).cachedIn(viewModelScope)

    fun fetchLocations(
        name: String
    ) = fetchLocationsUseCase(
        name = name
    ).cachedIn(viewModelScope)

    fun fetchEpisodes(
        name: String
    ) = fetchEpisodesUseCase(
        name = name
    ).cachedIn(viewModelScope)
}