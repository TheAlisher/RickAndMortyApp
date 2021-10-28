package com.alis.rickandmorty.presentation.ui.fragments.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.repositories.CharacterRepositoryImpl
import com.alis.rickandmorty.data.repositories.EpisodeRepositoryImpl
import com.alis.rickandmorty.data.repositories.LocationRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val characterRepository: CharacterRepositoryImpl,
    private val locationRepository: LocationRepositoryImpl,
    private val episodeRepository: EpisodeRepositoryImpl
) : BaseViewModel() {

    fun fetchCharacters(

    ) = characterRepository.fetchCharacters(

    ).cachedIn(viewModelScope)

    fun fetchLocations(

    ) = locationRepository.fetchLocations(

    ).cachedIn(viewModelScope)

    fun fetchEpisodes(

    ) = episodeRepository.fetchEpisodes(

    ).cachedIn(viewModelScope)
}