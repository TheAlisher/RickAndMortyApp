package com.alis.rickandmorty.domain.repositories

import androidx.paging.PagingData
import com.alis.rickandmorty.common.resource.Resource
import com.alis.rickandmorty.domain.models.episode.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    // TODO; PagingData cannot be in domain layer
    fun fetchEpisodes(
        name: String? = null,
        episode: String? = null
    ): Flow<PagingData<Episode>>

    fun fetchEpisode(id: Int): Flow<Resource<Episode>>
}