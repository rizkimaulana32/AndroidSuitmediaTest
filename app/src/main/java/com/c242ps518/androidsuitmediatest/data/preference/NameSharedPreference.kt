package com.c242ps518.androidsuitmediatest.data.preference

import android.content.Context
import android.content.SharedPreferences

class NameSharedPreference(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("name_preferences", Context.MODE_PRIVATE)

    companion object {
        const val NAME_KEY = "name_key"
    }

    fun saveName(name: String) {
        sharedPreferences.edit().putString(NAME_KEY, name).apply()
    }

    fun getName(): String {
        return sharedPreferences.getString(NAME_KEY, "") ?: ""
    }
}
