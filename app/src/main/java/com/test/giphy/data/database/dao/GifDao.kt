package com.test.giphy.data.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.test.giphy.data.model.Data

@Dao
abstract class GifDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertData(data: Data)

    @Delete
    abstract suspend fun deleteData(data: Data)


    @Query("select * from gif_table order by id")
    abstract fun getAllGifsLiveData(): PagingSource<Int, Data>

}