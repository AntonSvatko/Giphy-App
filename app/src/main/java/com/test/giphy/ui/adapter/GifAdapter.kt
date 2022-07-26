package com.test.giphy.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.test.giphy.R
import com.test.giphy.data.model.Data
import com.test.giphy.databinding.ItemGifBinding
import com.test.giphy.utill.glide.GlideListener


class GifAdapter(
    private val callback: (Int) -> Unit,
) : PagingDataAdapter<Data, GifAdapter.GifHolder>(
    GifCallback()
) {

    inner class GifHolder(private val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.isImageVisible = false
            binding.gifView.setGif(data)

            Log.d("work1", "salda")

            binding.gifView.setOnClickListener {
                callback(absoluteAdapterPosition)
            }

            binding.executePendingBindings()
        }

        private fun ShapeableImageView.setGif(gif: Data) {
            Glide.with(context).load(gif.images?.previewGif?.url)
                .listener(GlideListener {
                    binding.isImageVisible = true
                }).centerCrop().into(this)
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

    override fun onBindViewHolder(holder: GifHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }
}

class GifCallback : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}