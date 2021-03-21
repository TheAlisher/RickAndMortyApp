package com.alis.rickandmorty.base

import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentWithViewModel<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : BaseFragment(layoutId) {

    protected abstract val viewModel: ViewModel
    protected abstract val binding: Binding
}