package com.alis.rickandmorty.ui.fragments.episodes.detail

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.databinding.FragmentEpisodesDetailBinding
import com.alis.rickandmorty.ui.fragments.episodes.EpisodesViewModel

class EpisodesDetailFragment : BaseFragment<EpisodesViewModel, FragmentEpisodesDetailBinding>(
    R.layout.fragment_episodes_detail
) {

    override val viewModel: EpisodesViewModel by viewModels()
    override val binding by viewBinding(FragmentEpisodesDetailBinding::bind)
}