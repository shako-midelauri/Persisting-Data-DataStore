package com.example.datastore.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PrefsStoreImpl(context: Context) : PrefsStore {

    private val dataStore = context.createDataStore(
        name = STORE_NAME
    )

    override fun isNightMode() = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { it[PreferencesKeys.NIGHT_MODE_KEY] ?: false }

    override suspend fun toogleNightMode() {
        dataStore.edit {
            it[PreferencesKeys.NIGHT_MODE_KEY] = !(it[PreferencesKeys.NIGHT_MODE_KEY] ?: false)
        }
    }

    private object PreferencesKeys {
        val NIGHT_MODE_KEY = preferencesKey<Boolean>("dark_theme_enabled")
    }

    companion object {
        private const val STORE_NAME = "learning_data_store"
    }
}







