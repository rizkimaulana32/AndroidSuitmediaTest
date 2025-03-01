package com.c242ps518.androidsuitmediatest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.c242ps518.androidsuitmediatest.data.repository.UserRepository
import com.c242ps518.androidsuitmediatest.data.response.DataItem

class ThirdScreenViewModel(userRepository: UserRepository): ViewModel() {
    val quote: LiveData<PagingData<DataItem>> =
        userRepository.getUser().cachedIn(viewModelScope)

}