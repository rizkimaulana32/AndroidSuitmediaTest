package com.c242ps518.androidsuitmediatest.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.c242ps518.androidsuitmediatest.data.UserPagingSource
import com.c242ps518.androidsuitmediatest.data.response.DataItem
import com.c242ps518.androidsuitmediatest.data.retrofit.ApiService

class UserRepository(private val apiService: ApiService)  {
    fun getUser(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
                enablePlaceholders = false,
                prefetchDistance = 3
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}