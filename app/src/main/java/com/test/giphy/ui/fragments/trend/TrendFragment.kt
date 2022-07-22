package com.test.giphy.ui.fragments.trend

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import com.test.giphy.databinding.FragmentTrendBinding
import com.test.giphy.ui.adapter.GifAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrendFragment : Fragment() {
    private lateinit var binding: FragmentTrendBinding
    private val viewModel: TrendViewModel by viewModels()

    private val adapter by lazy {
        GifAdapter {

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
//        Log.d("test1",  "ASFA")
//        viewModel.getPhotos().map {
//            Log.d("test1", it.toString() + "ASFA")
//        }


        viewModel.viewModelScope.launch {
            viewModel.getPhotos().collectLatest {
//                Log.d("test1", it.toString() + "ASFA")
                adapter.submitData(it)
            }
        }

        binding.recyclerView.adapter = adapter
    }
}