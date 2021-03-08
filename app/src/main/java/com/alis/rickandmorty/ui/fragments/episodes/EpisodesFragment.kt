package com.alis.rickandmorty.ui.fragments.episodes

import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.databinding.FragmentEpisodesBinding

class EpisodesFragment : BaseFragment<EpisodesViewModel, FragmentEpisodesBinding>(
    R.layout.fragment_episodes
) {

    override val viewModel: EpisodesViewModel = TODO()
    override val binding: FragmentEpisodesBinding by viewBinding()

    override fun setupViews() {

    }

    override fun setupListeners() {

    }

    override fun setupObservers() {

    }
}