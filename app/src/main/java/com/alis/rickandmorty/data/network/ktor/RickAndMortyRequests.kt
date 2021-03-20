package com.alis.rickandmorty.data.network.ktor

import com.alis.rickandmorty.models.common.RickAndMortyResponse
import com.alis.rickandmorty.models.episode.Episode
import io.ktor.client.*
import io.ktor.client.request.*

class RickAndMortyRequests(
    private val client: HttpClient
) {

    suspend fun fetchEpisodes() = client.get<RickAndMortyResponse<Episode>>("/api/episode")
}