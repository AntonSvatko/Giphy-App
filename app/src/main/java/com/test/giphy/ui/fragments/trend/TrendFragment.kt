package com.test.giphy.ui.fragments.trend

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import com.test.giphy.R
import com.test.giphy.databinding.FragmentTrendBinding
import com.test.giphy.ui.adapter.GifAdapter
import com.test.giphy.ui.base.fragment.BaseFragment
import com.test.giphy.utill.DebouncedQueryTextListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TrendFragment : BaseFragment<FragmentTrendBinding>(R.layout.fragment_trend) {
    private val adapter: GifAdapter by lazy {
        GifAdapter {
            val action = TrendFragmentDirections.actionTrendFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listCreated.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
                binding.swipeRefresh.isRefreshing = false
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.update()
        }

        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    binding.progressBar.visibility = View.GONE
                }
                is LoadState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                else -> {}
            }
        }

        binding.recyclerView.adapter = adapter

        searchView()
        snackBar()
    }

    private fun searchView() {
        binding.searchView.setOnQueryTextListener(object : DebouncedQueryTextListener() {
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.debounceTextChange(newText ?: "")
                return true
            }
        })

        viewModel.isOnline.observe(viewLifecycleOwner) {
            binding.searchView.isVisible = it
        }
    }

    private fun snackBar() {
        val snackBar = Snackbar.make(
            binding.root,
            getString(R.string.offline_mode),
            Snackbar.LENGTH_INDEFINITE
        )

        viewModel.isOnline.observe(viewLifecycleOwner) {
            if (it)
                snackBar.dismiss()
            else
                snackBar.show()
        }
    }
}