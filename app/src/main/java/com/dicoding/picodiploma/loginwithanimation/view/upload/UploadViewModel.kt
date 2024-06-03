package com.dicoding.picodiploma.loginwithanimation.view.upload

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.loginwithanimation.data.UploadRepository
import java.io.File

class UploadViewModel(private val repository: UploadRepository) : ViewModel() {

    fun uploadImage(token: String, imageFile: File, description: String) = repository.uploadImage(token, imageFile, description)
}