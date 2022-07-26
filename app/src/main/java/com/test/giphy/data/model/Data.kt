package com.test.giphy.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gif_table")
data class Data(
    @PrimaryKey
    var id: String = "",
    var images: Images? = null,
    var title: String = "",
    var isDownloaded: Boolean = false
)