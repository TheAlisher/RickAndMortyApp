package com.alis.rickandmorty.ui.fragments.characters

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment<CharactersViewModel, FragmentCharactersBinding>(
    R.layout.fragment_characters
) {

    override val viewModel: CharactersViewModel by viewModels()
    override val binding: FragmentCharactersBinding by viewBinding()

    override fun setupViews() {

    }

    override fun setupListeners() {

    }

    override fun setupObservers() {

    }
}