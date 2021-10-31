package com.alis.rickandmorty.presentation.ui.fragments.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.domain.models.character.Character
import com.alis.rickandmorty.domain.usecases.character.FetchCharacterUseCase
import com.alis.rickandmorty.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val fetchCharacterUseCase: FetchCharacterUseCase
) : BaseViewModel() {

    private val _characterState = MutableLiveData<UIState<Character>>()
    val characterState: LiveData<UIState<Character>> = _characterState

    fun fetchCharacter(id: Int) {
        subscribeTo(_characterState) {
            fetchCharacterUseCase(id)
        }
    }
}