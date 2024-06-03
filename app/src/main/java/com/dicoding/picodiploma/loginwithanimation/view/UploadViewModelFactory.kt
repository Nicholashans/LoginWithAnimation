package com.dicoding.picodiploma.loginwithanimation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.loginwithanimation.data.UploadRepository
import com.dicoding.picodiploma.loginwithanimation.di.Injection
import com.dicoding.picodiploma.loginwithanimation.view.upload.UploadViewModel

class UploadViewModelFactory(private val repository: UploadRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UploadViewModel::class.java) -> {
                UploadViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: UploadViewModelFactory? = null

        @JvmStatic
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UploadViewModelFactory(Injection.provideUploadRepository())
            }.also { instance = it }
    }
}