package com.dicoding.picodiploma.loginwithanimation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.loginwithanimation.data.StoryRepository
import com.dicoding.picodiploma.loginwithanimation.di.Injection
import com.dicoding.picodiploma.loginwithanimation.view.main.StoryViewModel

class StoryViewModelFactory(private val repository: StoryRepository) :
    ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return when {
                modelClass.isAssignableFrom(StoryViewModel::class.java) -> {
                    StoryViewModel(repository) as T
                }
                else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
            }
        }

    companion object {
        @Volatile
        private var INSTANCE: StoryViewModelFactory? = null
        @JvmStatic
        fun getInstance(): StoryViewModelFactory {
            if (INSTANCE == null) {
                synchronized(StoryViewModelFactory::class.java) {
                    INSTANCE = StoryViewModelFactory(Injection.provideStoryRepository())
                }
            }
            return INSTANCE as StoryViewModelFactory
        }
    }
}