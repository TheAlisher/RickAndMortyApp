package com.alis.rickandmorty.data.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alis.rickandmorty.data.network.retrofit.RickAndMortyAPI
import com.alis.rickandmorty.data.repositories.RickAndMortyRepository
import com.alis.rickandmorty.models.character.Character
import com.alis.rickandmorty.models.common.RickAndMortyResponse
import retrofit2.HttpException
import java.io.IOException

private const val RICK_AND_MORTY_STARTING_PAGE_INDEX = 1

class RickAndMortyPagingSource(
    private val service: RickAndMortyAPI,
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: RICK_AND_MORTY_STARTING_PAGE_INDEX
        return try {
            val response = service.fetchCharacters(position)
            val nextPageNumber =
                Uri.parse(response.body()!!.info.next).getQueryParameter("page") as Int
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (IOE: IOException) {
            return LoadResult.Error(IOE)
        } catch (HttpE: HttpException) {
            return LoadResult.Error(HttpE)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}