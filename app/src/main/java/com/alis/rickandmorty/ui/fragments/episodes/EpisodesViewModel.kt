package com.alis.rickandmorty.ui.fragments.episodes

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.domain.usecases.episode.FetchEpisodeUseCase
import com.alis.rickandmorty.domain.usecases.episode.FetchEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val fetchEpisodesUseCase: FetchEpisodesUseCase,
    private val fetchEpisodeUseCase: FetchEpisodeUseCase
) : BaseViewModel() {

    fun fetchEpisodes() = fetchEpisodesUseCase().cachedIn(viewModelScope)

    fun fetchEpisode(id: Int) = fetchEpisodeUseCase(id)
}