package com.alis.rickandmorty.ui.fragments.locations

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.databinding.FragmentLocationsBinding

class LocationsFragment : BaseFragment<LocationsViewModel, FragmentLocationsBinding>(
    R.layout.fragment_locations
) {

    override val viewModel: LocationsViewModel by viewModels()
    override val binding: FragmentLocationsBinding by viewBinding()

    override fun setupViews() {

    }

    override fun setupListeners() {

    }

    override fun setupObservers() {

    }
}