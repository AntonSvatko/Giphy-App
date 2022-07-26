package com.test.giphy.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.test.giphy.R
import com.test.giphy.ui.fragments.MainViewModel

abstract class BaseFragment<T : ViewDataBinding>(private val resId: Int) : Fragment() {

    protected lateinit var binding: T
    protected val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, resId, container, false
        )

        binding.lifecycleOwner = this

        return binding.root
    }

}