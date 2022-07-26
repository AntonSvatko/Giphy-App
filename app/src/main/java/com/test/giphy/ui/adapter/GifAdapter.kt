package com.test.giphy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.test.giphy.R
import com.test.giphy.data.model.Data
import com.test.giphy.databinding.ItemGifBinding
import com.test.giphy.ui.base.adapter.BaseAdapter
import com.test.giphy.utill.glide.GlideListener


class GifAdapter(
    private val callback: (Int) -> Unit,
) : BaseAdapter() {
    inner class GifHolder(private val binding: ItemGifBinding) :
        BaseAdapter.BaseHolder(binding) {
        override fun bind(data: Data) {
            binding.isImageVisible = false
            binding.gifView.setGif(data, data.images?.previewGif?.url?: "").listener(GlideListener {
                binding.isImageVisible = true
            }).centerCrop().into(binding.gifView)

            binding.gifView.setOnClickListener {
                callback(absoluteAdapterPosition)
            }

            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifHolder {
        val binding: ItemGifBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_gif,
            parent, false
        )
        return GifHolder(binding)
    }
}
