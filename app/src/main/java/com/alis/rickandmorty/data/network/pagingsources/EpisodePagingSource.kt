package com.alis.rickandmorty.data.network.pagingsources

import com.alis.rickandmorty.base.BasePagingSource
import com.alis.rickandmorty.data.network.dtos.episode.toEpisode
import com.alis.rickandmorty.data.network.apiservices.EpisodeApiService
import com.alis.rickandmorty.data.network.dtos.episode.EpisodeDto
import com.alis.rickandmorty.domain.models.episode.Episode

class EpisodePagingSource(
    private val service: EpisodeApiService,
    private val name: String?,
    private val episode: String?
) : BasePagingSource<EpisodeDto, Episode>(
    { service.fetchEpisodes(it, name, episode) },
    { data -> data.map { it.toEpisode() } }
)