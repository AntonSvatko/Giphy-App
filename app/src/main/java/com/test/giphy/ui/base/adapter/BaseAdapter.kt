package com.test.giphy.ui.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.giphy.R
import com.test.giphy.data.model.Data
import com.test.giphy.databinding.ItemGifBinding
import com.test.giphy.ui.adapter.GifAdapter


abstract class BaseAdapter : PagingDataAdapter<Data, BaseAdapter.BaseHolder>(
    GifCallback()
) {

    open class BaseHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        open fun bind(data: Data) {

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