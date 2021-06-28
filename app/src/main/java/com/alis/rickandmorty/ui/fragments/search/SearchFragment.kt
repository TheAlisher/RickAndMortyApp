package com.alis.rickandmorty.ui.fragments.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.alis.rickandmorty.R
import com.alis.rickandmorty.base.BaseFragment
import com.alis.rickandmorty.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(
    R.layout.fragment_search
) {

    override val viewModel: SearchViewModel by viewModels()
    override val binding by viewBinding(FragmentSearchBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
    }
}