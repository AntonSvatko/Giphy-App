package com.test.giphy.ui.base.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.google.android.material.imageview.ShapeableImageView
import com.test.giphy.R
import com.test.giphy.data.model.Data
import com.test.giphy.databinding.ItemGifBinding
import com.test.giphy.ui.adapter.GifAdapter
import com.test.giphy.utill.glide.GlideListener
import java.io.File


abstract class BaseAdapter : PagingDataAdapter<Data, BaseAdapter.BaseHolder>(
    GifCallback()
) {

    open class BaseHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        open fun bind(data: Data) {

        }

        protected fun ShapeableImageView.setGif(data: Data, url: String): RequestBuilder<Drawable> {
            val dir = itemView.context.cacheDir.absolutePath
            val load = if(data.isDownloaded) File(dir, data.id + ".gif") else url

            return Glide.with(context).load(load)
        }
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
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