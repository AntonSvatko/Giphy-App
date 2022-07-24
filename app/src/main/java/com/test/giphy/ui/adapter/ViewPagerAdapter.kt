package com.test.giphy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.test.giphy.R
import com.test.giphy.data.model.Data
import com.test.giphy.databinding.ItemPagerBinding
import com.test.giphy.utill.GlideListener

class ViewPagerAdapter :
    PagingDataAdapter<Data, ViewPagerAdapter.ViewPagerHolder>(
        GifCallback()
    ) {

    inner class ViewPagerHolder(private val binding: ItemPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data) {
            binding.isImageVisible = false
            binding.pagerGifView.setGif(data)
            binding.executePendingBindings()
        }

        private fun ShapeableImageView.setGif(gif: Data) {
            Glide.with(this).load(gif.images.original.url)
                .listener(GlideListener {
                    binding.isImageVisible = true
                }).into(this)
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

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }
}