package com.test.giphy.ui.fragments.trend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.test.giphy.databinding.FragmentTrendBinding
import com.test.giphy.ui.adapter.GifAdapter
import com.test.giphy.util.getScreenWidth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TrendFragment : Fragment() {
    private lateinit var binding: FragmentTrendBinding
    private val viewModel: TrendViewModel by viewModels()

    private val adapter by lazy {
        GifAdapter (requireActivity().getScreenWidth()){

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewModelScope.launch {
            viewModel.getPhotos().collectLatest {
                adapter.submitData(it)
            }
        }
        binding.recyclerView.adapter = adapter
    }
}