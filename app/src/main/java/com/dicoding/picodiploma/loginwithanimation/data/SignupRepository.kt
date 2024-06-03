package com.dicoding.picodiploma.loginwithanimation.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.dicoding.picodiploma.loginwithanimation.data.api.ApiService
import com.dicoding.picodiploma.loginwithanimation.data.api.LoginResponse
import com.dicoding.picodiploma.loginwithanimation.data.api.RegisterResponse
import com.google.gson.Gson

class SignupRepository private constructor(
    private val apiService: ApiService
) {

    private val _isLoading = MutableLiveData<Boolean>()

    fun register(username: String, email: String, password: String) = liveData {
        emit(ResultState.Loading)
        _isLoading.value = true
        try {
            _isLoading.value = false
            val successResponse = apiService.register(username, email, password)
            emit(ResultState.Success(successResponse))
        } catch (e: retrofit2.HttpException) {
            _isLoading.value = false
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody,RegisterResponse::class.java)
            emit(errorResponse.message?.let { ResultState.Error(it) })
        }
    }

    fun login(email: String, password: String) = liveData {
        emit(ResultState.Loading)
        _isLoading.value = true
        try {
            _isLoading.value = false
            val successResponse = apiService.login(email, password)
            emit(ResultState.Success(successResponse))
        } catch (e: retrofit2.HttpException) {
            _isLoading.value = false
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(errorResponse.message?.let { ResultState.Error(it) })
        }
    }

    companion object {
        @Volatile
        private var instance: SignupRepository? = null

        fun getInstance(apiService: ApiService) =
            instance ?: synchronized(this) {
                instance ?: SignupRepository(apiService)
            }.also { instance = it }
        }
    }
