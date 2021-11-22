package com.alis.rickandmorty.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected abstract val viewModel: ViewModel
    protected abstract val binding: Binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupListeners()
        setupRequests()
        setupObservers()
    }

    protected open fun initialize() {
    }

    protected open fun setupListeners() {
    }

    protected open fun setupRequests() {
    }

    protected open fun setupObservers() {
    }
}