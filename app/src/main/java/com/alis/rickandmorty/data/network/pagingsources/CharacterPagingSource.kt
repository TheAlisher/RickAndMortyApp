package com.alis.rickandmorty.data.network.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alis.rickandmorty.data.network.apiservices.CharacterApiService
import com.alis.rickandmorty.data.network.dtos.character.toCharacter
import com.alis.rickandmorty.domain.models.character.Character
import retrofit2.HttpException
import java.io.IOException

private const val CHARACTER_STARTING_PAGE_INDEX = 1

class CharacterPagingSource(
    private val service: CharacterApiService,
    private val name: String?,
    private val status: String?,
    private val species: String?,
    private val type: String?,
    private val gender: String?
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: CHARACTER_STARTING_PAGE_INDEX
        return try {
            val response = service.fetchCharacters(position, name, status, species, type, gender)
            val next = response.info.next
            val nextPageNumber = if (next == null) {
                null
            } else {
                Uri.parse(response.info.next).getQueryParameter("page")!!.toInt()
            }

            LoadResult.Page(
                data = response.results.map { it.toCharacter() },
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