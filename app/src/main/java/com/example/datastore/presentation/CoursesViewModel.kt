package com.example.datastore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.datastore.App
import com.example.datastore.data.getCourseList
import com.example.datastore.data.preferences.PrefsStore
import com.example.datastore.data.preferences.PrefsStoreImpl
import kotlinx.coroutines.launch

class CoursesViewModel : ViewModel() {

    private val prefsStore: PrefsStore = PrefsStoreImpl(App.instance.applicationContext)

    val darkThemeEnabled = prefsStore.isNightMode().asLiveData()

    val courses = getCourseList().asLiveData()

    fun toggleNightMode() {
        viewModelScope.launch {
            prefsStore.toogleNightMode()
        }
    }
}