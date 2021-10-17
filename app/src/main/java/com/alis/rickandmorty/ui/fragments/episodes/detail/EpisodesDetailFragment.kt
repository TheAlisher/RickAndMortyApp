package com.alis.rickandmorty.ui.fragments.episodes.detail

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.data.resource.Resource
import com.alis.rickandmorty.databinding.FragmentEpisodesDetailBinding
import com.alis.rickandmorty.extensions.showToastShort
import com.alis.rickandmorty.domain.models.episode.Episode
import com.alis.rickandmorty.ui.fragments.episodes.EpisodesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodesDetailFragment : BaseFragment<EpisodesViewModel, FragmentEpisodesDetailBinding>(
    R.layout.fragment_episodes_detail
) {

    override val viewModel: EpisodesViewModel by activityViewModels()
    override val binding by viewBinding(FragmentEpisodesDetailBinding::bind)
    private val args: EpisodesDetailFragmentArgs by navArgs()

    override fun setupRequests() {
        lifecycleScope.launch {
            viewModel.fetchEpisode(args.id).collect {
                binding.progressBarEpisodesDetail.isVisible = it is Resource.Loading
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        it.data?.let { data -> subscribeToEpisodesSuccess(data) }
                    }
                    is Resource.Error -> {
                        it.message?.let { message -> showToastShort(message) }
                    }
                }
            }
        }
    }

    private fun subscribeToEpisodesSuccess(data: Episode) {
        //TODO
    }
}