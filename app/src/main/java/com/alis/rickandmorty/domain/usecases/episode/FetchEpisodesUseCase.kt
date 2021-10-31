package com.alis.rickandmorty.domain.usecases.episode

import com.alis.rickandmorty.domain.repositories.EpisodeRepository
import javax.inject.Inject

class FetchEpisodesUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(
        name: String? = null,
        episode: String? = null
    ) = repository.fetchEpisodes(
        name,
        episode
    )
}