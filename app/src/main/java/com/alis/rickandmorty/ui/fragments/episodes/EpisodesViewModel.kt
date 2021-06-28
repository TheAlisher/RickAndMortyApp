package com.alis.rickandmorty.ui.fragments.episodes

import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.repositories.EpisodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    fun fetchEpisodes() = repository.fetchEpisodes()

    fun fetchEpisode(id: Int) = repository.fetchEpisode(id)
}