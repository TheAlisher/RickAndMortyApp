package com.alis.rickandmorty.presentation.ui.fragments.episodes.detail

import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.common.base.BaseFragment
import com.alis.rickandmorty.common.extensions.showToastShort
import com.alis.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.alis.rickandmorty.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment : BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding>(
    R.layout.fragment_episode_detail
) {

    override val viewModel: EpisodeDetailViewModel by activityViewModels()
    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)
    private val args: EpisodeDetailFragmentArgs by navArgs()

    override fun setupRequests() {
        viewModel.fetchEpisode(args.id)
    }

    override fun setupObservers() {
        viewModel.episodeState.observe(viewLifecycleOwner, {
            binding.progressBarEpisodesDetail.isVisible = it is UIState.Loading
            when (it) {
                is UIState.Loading -> {
                }
                is UIState.Error -> {
                    showToastShort(it.error)
                }
                is UIState.Success -> {
                    //TODO
                }
            }
        })
    }
}