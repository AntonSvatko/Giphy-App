package com.test.giphy.ui.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.test.giphy.databinding.FragmentDetailsBinding
import com.test.giphy.ui.adapter.ViewPagerAdapter
import com.test.giphy.ui.fragments.trend.TrendViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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

        val adapter = ViewPagerAdapter()
        binding.viewPager.adapter = adapter


        Log.d("adapter1", viewModel.adapter?.itemCount.toString())
//        binding.viewPager.postDelayed({ binding.viewPager.setCurrentItem(args.page, false) }, 1000)


        lifecycleScope.launch {
            viewModel.getPhotos().collectLatest {
                adapter.submitData(lifecycle, it)
            }
        }

//        lifecycleScope.launch(Dispatchers.Main) {
//            binding.viewPager.setCurrentItem(args.page, true)
//            binding.viewPager.setCurrentItem(args.page, false)
//            binding.viewPager.adapter = adapter
//            delay(100)
//        }

        Log.d("args.page", args.page.toString())
    }
}