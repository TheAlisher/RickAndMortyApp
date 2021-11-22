package com.alis.rickandmorty.presentation.ui.fragments.episodes

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.common.base.BaseViewModel
import com.alis.rickandmorty.domain.usecases.episode.FetchEpisodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val fetchEpisodesUseCase: FetchEpisodesUseCase,
) : BaseViewModel() {

    fun fetchEpisodes() = fetchEpisodesUseCase().cachedIn(viewModelScope)
}