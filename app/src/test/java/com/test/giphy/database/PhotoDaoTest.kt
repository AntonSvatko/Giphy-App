package com.test.giphy.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.giphy.MockUtil
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class PhotoDaoTest : LocalDatabase() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun testPhotoDaoActions() = runBlocking {
        val photoDao = db.gifDao

        val mockData = MockUtil.createPhoto()

        photoDao.insertData(mockData)

        val listData = photoDao.getGifIds()
        assertThat(listData, hasItem(mockData.id))
    }

}