package com.alis.rickandmorty.ui.fragments.characters.detail

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.databinding.FragmentCharactersDetailBinding
import com.alis.rickandmorty.ui.fragments.characters.CharactersViewModel

class CharactersDetailFragment : BaseFragment<CharactersViewModel, FragmentCharactersDetailBinding>(
    R.layout.fragment_characters_detail
) {

    override val viewModel: CharactersViewModel by viewModels()
    override val binding by viewBinding(FragmentCharactersDetailBinding::bind)
}