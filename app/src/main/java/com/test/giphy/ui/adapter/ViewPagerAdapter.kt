package com.test.giphy.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.test.giphy.R
import com.test.giphy.data.model.Data
import com.test.giphy.databinding.ItemPagerBinding
import com.test.giphy.ui.base.adapter.BaseAdapter
import com.test.giphy.utill.glide.GlideListener

class ViewPagerAdapter(
    private val onDownload: (String, Drawable?, Data) -> Unit
) : BaseAdapter() {

    inner class ViewPagerHolder(private val binding: ItemPagerBinding) :
        BaseHolder(binding) {

        override fun bind(data: Data) {
            val dir = itemView.context.cacheDir.absolutePath

            binding.isImageVisible = false
            binding.pagerGifView.setGif(data, data.images?.original?.url?: "").listener(GlideListener {
                onDownload(dir, it, data)
                binding.isImageVisible = true
            }).into(binding.pagerGifView)

            binding.title.text = data.title
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding: ItemPagerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_pager,
            parent, false
        )
        return ViewPagerHolder(binding)
    }
}