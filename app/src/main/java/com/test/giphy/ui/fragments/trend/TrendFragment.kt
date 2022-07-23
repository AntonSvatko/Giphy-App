package com.test.giphy.ui.fragments.trend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.test.giphy.databinding.FragmentTrendBinding
import com.test.giphy.ui.adapter.GifAdapter
import com.test.giphy.utill.getScreenWidth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TrendFragment : Fragment() {
    private lateinit var binding: FragmentTrendBinding

    private val viewModel: TrendViewModel by activityViewModels()
    private lateinit var adapter: GifAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendBinding.inflate(inflater)
        adapter = GifAdapter(requireActivity().getScreenWidth()) {
            val action = TrendFragmentDirections.actionTrendFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.adapter?.let {
            adapter = it
        } ?: viewModel.viewModelScope.launch {
            viewModel.getPhotos().collectLatest {
                adapter.submitData(it)
            }
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

            }
        })

        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.adapter = adapter
    }
}