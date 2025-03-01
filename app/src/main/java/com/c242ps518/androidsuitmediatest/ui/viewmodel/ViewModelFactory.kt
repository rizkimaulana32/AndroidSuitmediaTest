package com.c242ps518.androidsuitmediatest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.c242ps518.androidsuitmediatest.data.repository.UserRepository
import com.c242ps518.androidsuitmediatest.data.retrofit.ApiConfig

class ViewModelFactory(
    private val userRepository: UserRepository,
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(
        ): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    UserRepository(ApiConfig.getApiService())
                )
            }.also { instance = it }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            return ThirdScreenViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
