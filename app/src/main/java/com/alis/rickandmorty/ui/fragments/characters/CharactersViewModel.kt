package com.alis.rickandmorty.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.domain.models.character.Character
import com.alis.rickandmorty.domain.usecases.character.FetchCharacterUseCase
import com.alis.rickandmorty.domain.usecases.character.FetchCharactersUseCase
import com.alis.rickandmorty.domain.usecases.episode.FetchEpisodeUseCase
import com.alish.boilerplate.presentation.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val fetchCharacterUseCase: FetchCharacterUseCase,
    private val fetchEpisodeUseCase: FetchEpisodeUseCase
) : BaseViewModel() {

    private val characterState = State<Character>()
    val characterLoading: LiveData<Boolean> = characterState.isLoading
    val characterError: LiveData<String> = characterState.error
    val characterData: LiveData<Character> = characterState.data

    fun fetchCharacters() = fetchCharactersUseCase().cachedIn(viewModelScope)

    fun fetchCharacter(id: Int) {
        viewModelScope.launch {
            subscribeTo(characterState) {
                fetchCharacterUseCase(id)
            }
        }
    }

    fun fetchEpisode(id: Int) = fetchEpisodeUseCase(id)
}