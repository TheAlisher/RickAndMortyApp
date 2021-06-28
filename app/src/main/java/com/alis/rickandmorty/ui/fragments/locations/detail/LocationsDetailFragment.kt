package com.alis.rickandmorty.ui.fragments.locations.detail

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.databinding.FragmentLocationsDetailBinding
import com.alis.rickandmorty.ui.fragments.locations.LocationsViewModel

class LocationDetailFragment : BaseFragment<LocationsViewModel, FragmentLocationsDetailBinding>(
    R.layout.fragment_locations_detail
) {

    override val viewModel: LocationsViewModel by viewModels()
    override val binding by viewBinding(FragmentLocationsDetailBinding::bind)
}