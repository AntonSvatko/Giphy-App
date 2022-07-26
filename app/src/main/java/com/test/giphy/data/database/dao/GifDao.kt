package com.test.giphy.data.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.*
import com.test.giphy.data.model.Data

@Dao
abstract class GifDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertData(data: Data)

    @Delete
    abstract suspend fun deleteData(data: Data)

    @Update
    abstract suspend fun updateData(data: Data)

    @Query("select * from gif_table where id = :id")
    abstract suspend fun getData(id: String): Data?

//    @Query("select * from gif_table")
//    abstract fun getAllGifsLiveData(): PagingSource<Int, Data>

    @Query("select * from gif_table order by id")
    abstract fun getAllGifsLiveData(): PagingSource<Int, Data>

//    @Query("select id from gif_table")
//    abstract suspend fun getAllGifsIds(): List<String>

}