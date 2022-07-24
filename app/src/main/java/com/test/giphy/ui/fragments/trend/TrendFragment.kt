package com.test.giphy.ui.fragments.trend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.test.giphy.databinding.FragmentTrendBinding
import com.test.giphy.ui.adapter.GifAdapter
import com.test.giphy.utill.getScreenWidth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TrendFragment : Fragment() {
    private lateinit var binding: FragmentTrendBinding
    private val viewModel: TrendViewModel by activityViewModels()

    private val adapter: GifAdapter by lazy {
        GifAdapter(requireActivity().getScreenWidth()) {
            val action = TrendFragmentDirections.actionTrendFragmentToDetailsFragment(it)
//            lifecycleScope.launch {
//                //Your adapter's loadStateFlow here
//                adapter.loadStateFlow.distinctUntilChangedBy {
//                    it.refresh
//                }.collect {
//                    viewModel.listCreated = flow {
//                        emit(adapter.snapshot())
//                    }
//                }
//            }
            findNavController().navigate(action)
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

        viewModel.adapter?.let {
            viewModel.adapter = it
        } ?: viewModel.viewModelScope.launch {
            viewModel.getPhotos().collectLatest {
                viewModel.listCreated = flow {
                    emit(it)
                }
                adapter.submitData(it)
            }
        }

        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.adapter = adapter
    }

}