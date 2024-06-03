package com.dicoding.picodiploma.loginwithanimation.di

import android.content.Context
import com.dicoding.picodiploma.loginwithanimation.data.SignupRepository
import com.dicoding.picodiploma.loginwithanimation.data.StoryRepository
import com.dicoding.picodiploma.loginwithanimation.data.UploadRepository
import com.dicoding.picodiploma.loginwithanimation.data.UserRepository
import com.dicoding.picodiploma.loginwithanimation.data.api.ApiConfig
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserPreference
import com.dicoding.picodiploma.loginwithanimation.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }

    fun provideSignupRepository(): SignupRepository {
        val apiService = ApiConfig.getApiService()
        return SignupRepository.getInstance(apiService)
    }

    fun provideUploadRepository(): UploadRepository {
        val apiService = ApiConfig.getApiService()
        return UploadRepository.getInstance(apiService)
    }

    fun provideStoryRepository(): StoryRepository {
        return StoryRepository.getInstance()
    }
}