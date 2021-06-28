package com.alis.rickandmorty.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentWithMenu<ViewModel : BaseViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : BaseFragment<ViewModel, Binding>(
    layoutId
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
}