package com.test.giphy.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.giphy.data.database.converters.ImagesConverter
import com.test.giphy.data.database.dao.GifDao
import com.test.giphy.data.model.Data

@TypeConverters(ImagesConverter::class)
@Database(entities = [Data::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract val favoritesDao: GifDao
}