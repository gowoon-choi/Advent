package com.hbd.advent.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val token = stringPreferencesKey("token")
    val nickname = stringPreferencesKey("nickname")
}