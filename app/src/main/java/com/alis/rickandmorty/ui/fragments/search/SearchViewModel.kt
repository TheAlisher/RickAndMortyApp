package com.alis.rickandmorty.ui.fragments.search

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.repositories.CharacterRepository
import com.alis.rickandmorty.data.repositories.EpisodeRepository
import com.alis.rickandmorty.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val locationRepository: LocationRepository,
    private val episodeRepository: EpisodeRepository
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