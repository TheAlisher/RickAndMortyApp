package com.alis.rickandmorty.data.repositories

import com.alis.rickandmorty.common.base.BaseRepository
import com.alis.rickandmorty.data.network.dtos.episode.toEpisode
import com.alis.rickandmorty.data.network.apiservices.EpisodeApiService
import com.alis.rickandmorty.data.network.pagingsources.EpisodePagingSource
import com.alis.rickandmorty.domain.repositories.EpisodeRepository
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val service: EpisodeApiService
) : BaseRepository(), EpisodeRepository {

    override fun fetchEpisodes(
        name: String?,
        episode: String?
    ) = doPagingRequest(
        EpisodePagingSource(service, name, episode)
    )

    override fun fetchEpisode(id: Int) = doRequest {
        service.fetchEpisode(id).toEpisode()
    }
}