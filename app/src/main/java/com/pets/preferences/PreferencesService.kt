package com.pets.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface PreferencesService {
    fun setEmail(email: String)
    fun getEmail(): String?
    fun clear()
}

class PreferencesServiceImpl @Inject constructor(
    @ApplicationContext appContext: Context
) : PreferencesService {

    private var preferences = appContext.getSharedPreferences(
        "petsApp", Context.MODE_PRIVATE
    )

    override fun setEmail(email: String) {
        val edit = preferences.edit()
        edit.putString(EMAIL, email)
        edit.apply()
    }

    override fun getEmail(): String? {
        return preferences.getString(EMAIL, null)
    }

    override fun clear() {
       preferences.edit().clear().apply()
    }

    private companion object {
        const val EMAIL = "email"
    }
}