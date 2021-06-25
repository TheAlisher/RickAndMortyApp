package com.alis.rickandmorty.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alis.rickandmorty.base.BaseRepository
import com.alis.rickandmorty.data.network.retrofit.apiservices.EpisodeApiService
import com.alis.rickandmorty.data.repositories.pagingsources.EpisodePagingSource
import com.alis.rickandmorty.models.episode.Episode
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val episodeApiService: EpisodeApiService
) : BaseRepository() {

    fun fetchEpisodes(): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                EpisodePagingSource(episodeApiService)
            }
        ).flow
    }
}