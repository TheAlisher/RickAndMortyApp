package com.alis.rickandmorty.ui.fragments.characters

import com.alis.rickandmorty.base.BaseViewModel
import com.alis.rickandmorty.data.repositories.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: RickAndMortyRepository
) : BaseViewModel() {

}