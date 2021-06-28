package com.alis.rickandmorty.ui.fragments.characters

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.repositories.CharacterRepository
import com.alis.rickandmorty.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository,
    private val episodeRepository: EpisodeRepository
) : BaseViewModel() {

    fun fetchCharacters() = repository.fetchCharacters().cachedIn(viewModelScope)

    fun fetchCharacter(id: Int) = repository.fetchCharacter(id)

    fun fetchEpisode(id: Int) = episodeRepository.fetchEpisode(id)
}