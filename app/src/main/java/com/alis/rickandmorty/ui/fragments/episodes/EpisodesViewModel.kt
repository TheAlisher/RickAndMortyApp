package com.alis.rickandmorty.ui.fragments.episodes

import androidx.lifecycle.LiveData
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.network.Resource
import com.alis.rickandmorty.data.repositories.EpisodeRepository
import com.alis.rickandmorty.models.common.RickAndMortyResponse
import com.alis.rickandmorty.models.episode.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: EpisodeRepository
) : BaseViewModel() {

    fun fetchEpisodes(): LiveData<Resource<RickAndMortyResponse<Episode>>> {
        return repository.fetchEpisodes()
    }
}