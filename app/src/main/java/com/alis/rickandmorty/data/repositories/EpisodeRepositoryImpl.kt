package com.alis.rickandmorty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alis.rickandmorty.base.BaseRepository
import com.alis.rickandmorty.data.network.dtos.episode.toEpisode
import com.alis.rickandmorty.data.network.apiservices.EpisodeApiService
import com.alis.rickandmorty.data.network.pagingsources.EpisodePagingSource
import com.alis.rickandmorty.domain.repositories.EpisodeRepository
import com.alis.rickandmorty.domain.models.episode.Episode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val service: EpisodeApiService
) : BaseRepository(), EpisodeRepository {

    override fun fetchEpisodes(
        name: String?,
        episode: String?
    ): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service, name, episode)
            }
        ).flow
    }

    override fun fetchEpisode(id: Int) = doRequest {
        service.fetchEpisode(id).toEpisode()
    }
}