package com.test.giphy.ui.fragments.trend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.test.giphy.databinding.FragmentTrendBinding
import com.test.giphy.ui.adapter.GifAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TrendFragment : Fragment() {
    private lateinit var binding: FragmentTrendBinding
    private val viewModel: TrendViewModel by activityViewModels()

    private val adapter: GifAdapter by lazy {
        GifAdapter {
            val action = TrendFragmentDirections.actionTrendFragmentToDetailsFragment(it)
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

        viewModel.listCreated.observe(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.IO) {
                adapter.submitData(it)
            }
        }

        binding.recyclerView.adapter = adapter
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.savedAdapter = true
    }

}