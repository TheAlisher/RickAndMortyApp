package com.alis.rickandmorty.ui.fragments.characters.detail

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.data.resource.Resource
import com.alis.rickandmorty.databinding.FragmentCharactersDetailBinding
import com.alis.rickandmorty.extensions.showToastShort
import com.alis.rickandmorty.models.character.Character
import com.alis.rickandmorty.ui.fragments.characters.CharactersViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CharactersDetailFragment : BaseFragment<CharactersViewModel, FragmentCharactersDetailBinding>(
    R.layout.fragment_characters_detail
) {

    override val viewModel: CharactersViewModel by activityViewModels()
    override val binding by viewBinding(FragmentCharactersDetailBinding::bind)
    private val args: CharactersDetailFragmentArgs by navArgs()

    override fun setupRequests() {
        lifecycleScope.launch {
            viewModel.fetchCharacter(args.id).collect {
                binding.progressBarCharactersDetail.isVisible = it is Resource.Loading
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        it.data?.let { data -> subscribeToCharacterSuccess(data) }
                    }
                    is Resource.Error -> {
                        it.message?.let { message -> showToastShort(message) }
                    }
                }
            }
        }
    }

    private fun subscribeToCharacterSuccess(data: Character) {
        binding.apply {
            imageCharactersDetail.load(data.image)
        }
    }
}