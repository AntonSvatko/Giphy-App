package com.test.giphy

import com.test.giphy.data.model.Data
import com.test.giphy.data.model.Images
import com.test.giphy.data.model.Original
import com.test.giphy.data.model.PreviewGif

object MockUtil {

    fun createList() = listOf(createPhoto())


    fun createPhoto() = Data(
        "1",
        Images(
            Original(
                "400",
                "1231",
                "https://media1.giphy.com/media/Zv7dMXBfyTSA0QKSyO/giphy.gif?cid=372e9504ymk7sxjvmz27lxcej37jplb5bfox3cfksvp8i0aj&rid=giphy.gif&ct=g",
                "400"
            ),
            PreviewGif(
                "54",
                "532",
                "https://media1.giphy.com/media/Zv7dMXBfyTSA0QKSyO/giphy-preview.gif?cid=372e9504ymk7sxjvmz27lxcej37jplb5bfox3cfksvp8i0aj&rid=giphy-preview.gif&ct=g",
                "54"
            )
        ),
        "title",
        false
    )


}