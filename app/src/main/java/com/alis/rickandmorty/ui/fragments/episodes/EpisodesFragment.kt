package com.alis.rickandmorty.ui.fragments.episodes

import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragmentWithViewModel
import com.alis.rickandmorty.data.network._Resource
import com.alis.rickandmorty.databinding.FragmentEpisodesBinding
import com.alis.rickandmorty.extensions.gone
import com.alis.rickandmorty.extensions.showToastShort
import com.alis.rickandmorty.extensions.visible
import com.alis.rickandmorty.ui.adapters.EpisodeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesFragment : BaseFragmentWithViewModel<EpisodesViewModel, FragmentEpisodesBinding>(
    R.layout.fragment_episodes
) {

    override val viewModel: EpisodesViewModel by viewModels()
    override val binding: FragmentEpisodesBinding by viewBinding()

    private val episodeAdapter = EpisodeAdapter(this::onItemClick)

    override fun setupViews() {
        binding.recyclerEpisode.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = episodeAdapter
        }
    }

    override fun setupListeners() {

    }

    private fun onItemClick() {
        //TODO
    }

    override fun setupObservers() {
        viewModel.fetchEpisodes().observe(viewLifecycleOwner, {
            when (it) {
                is _Resource.Loading -> {
                    binding.progressEpisodesLoader.visible()
                }
                is _Resource.Error -> {
                    binding.progressEpisodesLoader.gone()
                    showToastShort(it.message.toString())
                }
                is _Resource.Success -> {
                    binding.progressEpisodesLoader.gone()
                    episodeAdapter.submitList(it.data?.results!!)
                }
            }
        })
    }
}