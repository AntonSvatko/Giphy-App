package com.test.giphy.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.google.android.material.imageview.ShapeableImageView
import com.test.giphy.R
import com.test.giphy.data.model.Data
import com.test.giphy.databinding.ItemPagerBinding
import com.test.giphy.ui.adapter.glide.GlideListener
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer

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

        private fun ShapeableImageView.setGif(data: Data) {
            Glide.with(this).load(data.images.original.url)
                .listener(GlideListener {
                    binding.isImageVisible = true
                    downloadGif(it, data)
                }).into(this)
        }

        private fun downloadGif(drawable: Drawable?, data: Data){
            val localDirectory = itemView.context.cacheDir.absolutePath
            val byteBuffer = (drawable as GifDrawable).buffer
            val gifFile = File(localDirectory, data.id + ".gif")

            val output = FileOutputStream(gifFile)
            val bytes = ByteArray(byteBuffer.capacity())
            (byteBuffer.duplicate().clear() as ByteBuffer).get(bytes)
            output.write(bytes, 0 ,bytes.size)
            output.close()
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