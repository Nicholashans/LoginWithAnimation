package com.dicoding.picodiploma.loginwithanimation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.loginwithanimation.data.SignupRepository
import com.dicoding.picodiploma.loginwithanimation.di.Injection
import com.dicoding.picodiploma.loginwithanimation.view.signup.SignupViewModel

class SignupViewModelFactory(private val repository: SignupRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SignupViewModelFactory? = null
        @JvmStatic
        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SignupViewModelFactory(Injection.provideSignupRepository())
            }.also { INSTANCE = it }
    }
}