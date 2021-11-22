package com.alis.rickandmorty.presentation.ui.fragments.episodes.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alis.rickandmorty.common.base.BaseViewModel
import com.alis.rickandmorty.domain.models.episode.Episode
import com.alis.rickandmorty.domain.usecases.episode.FetchEpisodeUseCase
import com.alis.rickandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val fetchEpisodeUseCase: FetchEpisodeUseCase
) : BaseViewModel() {

    private val _episodeState = MutableLiveData<UIState<Episode>>()
    val episodeState: LiveData<UIState<Episode>> = _episodeState

    fun fetchEpisode(id: Int) {
        subscribeTo(_episodeState) {
            fetchEpisodeUseCase(id)
        }
    }
}