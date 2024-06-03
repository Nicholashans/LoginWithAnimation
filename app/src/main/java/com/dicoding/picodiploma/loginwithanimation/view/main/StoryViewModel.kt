package com.dicoding.picodiploma.loginwithanimation.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.loginwithanimation.data.StoryRepository
import com.dicoding.picodiploma.loginwithanimation.data.api.ListStoryItem

class StoryViewModel(private val repository: StoryRepository) : ViewModel() {

    val isLoading: LiveData<Boolean> = repository.isLoading
    val story: LiveData<List<ListStoryItem?>> = repository.story

    fun getStories(token: String) = repository.getStories(token)
}