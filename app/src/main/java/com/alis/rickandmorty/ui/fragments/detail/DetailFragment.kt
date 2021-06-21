package com.alis.rickandmorty.ui.fragments.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.databinding.FragmentDetailBinding
import com.alis.rickandmorty.extensions.showToastShort
import com.alis.rickandmorty.models.enums.FromWhere
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>(
    R.layout.fragment_detail
) {

    override val viewModel: DetailViewModel by viewModels()
    override val binding: FragmentDetailBinding by viewBinding()
    private val args: DetailFragmentArgs by navArgs()

    override fun initialize() {
        when (args.fromWhere) {
            FromWhere.CHARACTERS -> {
                showToastShort(R.string.fragment_label_characters)
            }
            FromWhere.LOCATIONS -> {
                showToastShort(R.string.fragment_label_locations)
            }
            FromWhere.EPISODES -> {
                showToastShort(R.string.fragment_label_episodes)
            }
        }
        binding.textDetail.text = args.id.toString()
    }
}