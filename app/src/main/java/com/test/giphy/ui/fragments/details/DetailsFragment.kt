package com.test.giphy.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.test.giphy.R
import com.test.giphy.data.model.Data
import com.test.giphy.databinding.FragmentDetailsBinding
import com.test.giphy.ui.adapter.ViewPagerAdapter
import com.test.giphy.ui.base.fragment.BaseFragment
import com.test.giphy.ui.fragments.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {
    private val args: DetailsFragmentArgs by navArgs()
    private var currentItem: Data? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = savedInstanceState?.getInt(KEY_POSITION) ?: args.page

        val adapter = ViewPagerAdapter { dir, drawable, data ->
            currentItem = data
            viewModel.downloadGif(dir, drawable, data)
        }

        binding.delete.setOnClickListener {
            currentItem?.let { item ->
                viewModel.deleteGif(requireContext().cacheDir.absolutePath,
                    item
                )
            }
        }

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.viewPager.adapter = adapter
        binding.viewModel = viewModel

        binding.viewPager.doOnPreDraw {
            binding.viewPager.setCurrentItem(position, false)
        }

        viewModel.listCreated.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_POSITION, binding.viewPager.currentItem)
    }

    companion object {
        private const val KEY_POSITION = "position"
    }
}