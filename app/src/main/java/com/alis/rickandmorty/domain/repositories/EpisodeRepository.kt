package com.alis.rickandmorty.domain.repositories

import androidx.paging.PagingData
import com.alis.rickandmorty.data.resource.Resource
import com.alis.rickandmorty.domain.models.episode.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    fun fetchEpisodes(
        name: String? = null,
        episode: String? = null
    ): Flow<PagingData<Episode>>

    fun fetchEpisode(id: Int): Flow<Resource<Episode>>
}