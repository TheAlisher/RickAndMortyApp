package com.alis.rickandmorty.presentation.ui.fragments.characters.detail

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.databinding.FragmentCharactersDetailBinding
import com.alis.rickandmorty.common.extensions.showToastShort
import com.alis.rickandmorty.domain.models.character.Character
import com.alis.rickandmorty.presentation.state.UIState
import com.alis.rickandmorty.presentation.ui.fragments.characters.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersDetailFragment : BaseFragment<CharactersViewModel, FragmentCharactersDetailBinding>(
    R.layout.fragment_characters_detail
) {

    override val viewModel: CharactersViewModel by activityViewModels()
    override val binding by viewBinding(FragmentCharactersDetailBinding::bind)
    private val args: CharactersDetailFragmentArgs by navArgs()

    override fun setupRequests() {
        viewModel.fetchCharacter(args.id)
    }

    override fun setupObservers() {
        subscribeToFoo()
    }

    private fun subscribeToFoo() {
        viewModel.characterState.observe(viewLifecycleOwner, {
            binding.progressBarCharactersDetail.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Loading -> {
                }
                is UIState.Error -> {
                    showToastShort(it.error)
                }
                is UIState.Success -> {
                    binding.imageCharactersDetail.load(it.data.image)
                }
            }
        })
    }
}