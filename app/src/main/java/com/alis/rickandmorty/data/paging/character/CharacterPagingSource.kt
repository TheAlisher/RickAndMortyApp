package com.alis.rickandmorty.data.paging.character

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alis.rickandmorty.data.network.retrofit.RickAndMortyAPI
import com.alis.rickandmorty.models.character.Character
import retrofit2.HttpException
import java.io.IOException

private const val CHARACTER_STARTING_PAGE_INDEX = 30

class CharacterPagingSource(
    private val APIService: RickAndMortyAPI,
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: CHARACTER_STARTING_PAGE_INDEX
        return try {
            val response = APIService.fetchCharacters(position)
            val repos = response.body()
            val next = repos?.info?.next
            val nextPageNumber = if (next == null) {
                null
            } else {
                Uri.parse(repos.info.next).getQueryParameter("page")!!.toInt()
            }

            LoadResult.Page(
                data = repos!!.results,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}