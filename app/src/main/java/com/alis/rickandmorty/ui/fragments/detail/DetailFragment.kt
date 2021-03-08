package com.alis.rickandmorty.ui.fragments.detail

import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>(
    R.layout.fragment_detail
) {

    override val viewModel: DetailViewModel = TODO()
    override val binding: FragmentDetailBinding by viewBinding()

    override fun setupViews() {

    }

    override fun setupListeners() {

    }

    override fun setupObservers() {

    }
}