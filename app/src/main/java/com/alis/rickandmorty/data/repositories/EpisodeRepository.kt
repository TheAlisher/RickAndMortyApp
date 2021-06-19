package com.alis.rickandmorty.data.repositories

import com.alis.rickandmorty.base.BaseRepository
import com.alis.rickandmorty.data.network._Resource
import com.alis.rickandmorty.data.network.ktor.EpisodeApiService
import com.alis.rickandmorty.models.common.RickAndMortyResponse
import com.alis.rickandmorty.models.episode.Episode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val episodeApiService: EpisodeApiService
) : BaseRepository() {

    fun fetchEpisodes() = doRequest {
        episodeApiService.fetchEpisodes()
    }
}