package com.test.giphy.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.giphy.data.model.Images

class ImagesConverter {

    private val gson: Gson = Gson()

    @TypeConverter
    fun fromJson(json: String): Images {
        return gson.fromJson(json, object : TypeToken<Images>() {}.type)
    }

    @TypeConverter
    fun toJson(sizes: Images): String {
        return gson.toJson(sizes)
    }

}