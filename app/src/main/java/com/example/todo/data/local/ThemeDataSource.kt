package com.example.todo.data.local

import android.content.SharedPreferences

class ThemeDataSource(private val sharedPreferences: SharedPreferences) {

    fun putIsDark(isDark: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(IS_DARK_KEY, isDark)
            .apply()
    }

    fun getIsDark(): Boolean {
        return sharedPreferences.getBoolean(IS_DARK_KEY, false)
    }

    private companion object {
        const val IS_DARK_KEY = "is_dark_theme"
    }
}
