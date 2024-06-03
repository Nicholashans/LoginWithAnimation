package com.dicoding.picodiploma.loginwithanimation.view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class PasswordEditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    private var isError = false

    init {
        addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (isError) return
                if (s.length < 8) {
                    isError = true
                    setError("Password tidak boleh kurang dari 8 karakter", null)
                    isError = false
                } else {
                    error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

}