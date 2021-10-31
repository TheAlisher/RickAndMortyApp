package com.alis.rickandmorty.presentation.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.domain.models.character.Character
import com.alis.rickandmorty.domain.usecases.character.FetchCharacterUseCase
import com.alis.rickandmorty.domain.usecases.character.FetchCharactersUseCase
import com.alis.rickandmorty.domain.usecases.episode.FetchEpisodeUseCase
import com.alis.rickandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val fetchCharacterUseCase: FetchCharacterUseCase,
    private val fetchEpisodeUseCase: FetchEpisodeUseCase
) : BaseViewModel() {

    private val _characterState = MutableLiveData<UIState<Character>>()
    val characterState: LiveData<UIState<Character>> = _characterState

    fun fetchCharacters() = fetchCharactersUseCase().cachedIn(viewModelScope)

    fun fetchCharacter(id: Int) {
        subscribeTo(_characterState) {
            fetchCharacterUseCase(id)
        }
    }

    fun fetchEpisode(id: Int) = fetchEpisodeUseCase(id)
}