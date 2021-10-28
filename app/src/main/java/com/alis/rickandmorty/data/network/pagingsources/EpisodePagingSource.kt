package com.alis.rickandmorty.data.network.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alis.rickandmorty.data.network.dtos.episode.toEpisode
import com.alis.rickandmorty.data.network.apiservices.EpisodeApiService
import com.alis.rickandmorty.domain.models.episode.Episode
import retrofit2.HttpException
import java.io.IOException

private const val EPISODE_STARTING_PAGE_INDEX = 1

class EpisodePagingSource(
    private val service: EpisodeApiService,
    private val name: String?,
    private val episode: String?
) : PagingSource<Int, Episode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val position = params.key ?: EPISODE_STARTING_PAGE_INDEX
        return try {
            val response = service.fetchEpisodes(position, name, episode)
            val next = response.info.next
            val nextPageNumber = if (next == null) {
                null
            } else {
                Uri.parse(response.info.next).getQueryParameter("page")!!.toInt()
            }

            LoadResult.Page(
                data = response.results.map { it.toEpisode() },
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}