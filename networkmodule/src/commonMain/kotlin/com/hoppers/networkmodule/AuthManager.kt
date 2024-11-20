package com.jk.networkmodule

import android.content.SharedPreferences
import com.jk.networkmodule.model.UserProfile
import kotlinx.serialization.json.Json

object AuthManager {

    private lateinit var sharedPreferences: SharedPreferences
    private var onLogout: (() -> Unit) = { }

    fun initialize(
        sharedPreferences: SharedPreferences,
        onLogout: () -> Unit
    ) {
        this.onLogout = onLogout
        AuthManager.sharedPreferences = sharedPreferences
    }

    fun saveAccessToken(token: String) {
        sharedPreferences.edit().putString("access_token", token).apply()
    }

    fun getAccessToken(): String? {
        return sharedPreferences.getString("access_token", null)
    }

    fun getLogin(): String? {
        return sharedPreferences.getString("login", null)
    }

fun getUserProfile(): UserProfile? {
    val jsonString = sharedPreferences.getString("user_data", null)
    return jsonString?.let {
        Json.decodeFromString<UserProfile>(it)
    }
}

    fun saveUserData(data: String) {
        sharedPreferences.edit().putString("user_data", data).apply()
    }

}