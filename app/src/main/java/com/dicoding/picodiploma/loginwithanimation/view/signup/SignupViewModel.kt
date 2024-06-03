package com.dicoding.picodiploma.loginwithanimation.view.signup

import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.loginwithanimation.data.SignupRepository

class SignupViewModel(private val repository: SignupRepository) : ViewModel() {

    fun registerUser(username: String, email: String, password: String) = repository.register(username, email, password)

    fun loginUser(email: String, password: String) = repository.login(email, password)
}