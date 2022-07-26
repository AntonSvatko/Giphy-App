package com.test.giphy.ui.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.test.giphy.databinding.FragmentDetailsBinding
import com.test.giphy.ui.adapter.ViewPagerAdapter
import com.test.giphy.ui.fragments.trend.TrendViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    private val viewModel: TrendViewModel by activityViewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = savedInstanceState?.getInt(KEY_POSITION) ?: args.page

        val adapter = ViewPagerAdapter { dir, drawable, data ->
            binding.isDownloaded = data.isDownloaded
            viewModel.downloadGif(dir, drawable, data)
        }
        binding.viewPager.adapter = adapter

        binding.viewPager.doOnPreDraw {
            binding.viewPager.setCurrentItem(position, false)
        }

        lifecycleScope.launch {
            viewModel.listCreated.observe(viewLifecycleOwner) {
                adapter.submitData(lifecycle, it)
            }
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