package com.ilham.javaline.data.database

import android.content.Context
import android.content.SharedPreferences
import hu.autsoft.krate.Krate
import hu.autsoft.krate.booleanPref
import hu.autsoft.krate.stringPref

class PrefsManager(context: Context) : Krate {

    private val PREFS_IS_LOGIN: String = "prefs_is_login"
    private val PREFS_ID: String = "prefs_id"
    private val PREFS_IS_KODE_USER: String = "prefs_is_kode_user"
    private val PREFS_IS_ROLE: String = "prefs_is_role"
    private val PREFS_IS_PASSWORD: String = "prefs_is_password"
    private val PREFS_IS_PASSWORD_CONFIRMATION: String = "prefs_is_password_confirmation"


    override val sharedPreferences: SharedPreferences

    init {
        sharedPreferences =context.applicationContext.getSharedPreferences(
            "Javaline",Context.MODE_PRIVATE
        )
    }

    var prefIsLogin by booleanPref(PREFS_IS_LOGIN, false)
    var prefsId by stringPref(PREFS_ID, "")
    var prefs_is_kode_user by stringPref(PREFS_IS_KODE_USER, "")
    var prefs_is_role by stringPref(PREFS_IS_ROLE, "")
    var prefs_is_password by stringPref(PREFS_IS_PASSWORD, "")
    var prefs_is_password_confirmation by stringPref(PREFS_IS_PASSWORD_CONFIRMATION, "")

    fun logout(){
        sharedPreferences.edit().clear().apply()
    }
}