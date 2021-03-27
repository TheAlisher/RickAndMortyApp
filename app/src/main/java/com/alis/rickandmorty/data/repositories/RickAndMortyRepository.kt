package com.alis.rickandmorty.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.*
import com.alis.rickandmorty.data.local.CharacterDao
import com.alis.rickandmorty.data.network.Resource
import com.alis.rickandmorty.data.network.ktor.RickAndMortyRequests
import com.alis.rickandmorty.data.network.retrofit.RickAndMortyAPI
import com.alis.rickandmorty.data.paging.character.CharacterPagingSource
import com.alis.rickandmorty.models.character.Character
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(
    private val api: RickAndMortyAPI,
    private val characterDao: CharacterDao,
    private val requests: RickAndMortyRequests
) {

    fun fetchCharacters(): LiveData<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(api)
            }
        ).liveData
    }

    /*fun fetchCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                RickAndMortyPagingSource(api)
            }
        ).flow
    }*/

    /*fun fetchCharacters(
        page: Int
    ) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val request = Resource.success(data = api.fetchCharacters(page))
            emit(request)
            saveCharacters(request.data?.body()?.results!!)
        } catch (E: Exception) {
            emit(Resource.error(data = null, message = E.message ?: "Error Occurred!"))
        }
    }*/

    private fun saveCharacters(characters: MutableList<Character>) {
        characterDao.insertAll(characters)
    }

    fun loadCharacters(): MutableList<Character> {
        return characterDao.getAll()
    }

    fun fetchLocations() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.fetchLocations()))
        } catch (E: Exception) {
            emit(Resource.error(data = null, message = E.message ?: "Error Occurred!"))
        }
    }

    fun fetchEpisodes() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = requests.fetchEpisodes()))
        } catch (E: Exception) {
            emit(Resource.error(data = null, message = E.message ?: "Error Occurred!"))
        }
    }
}