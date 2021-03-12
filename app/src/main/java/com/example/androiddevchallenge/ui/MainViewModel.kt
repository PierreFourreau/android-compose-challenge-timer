package com.example.androiddevchallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var job: Job? = null
    private val _seconds = MutableStateFlow(0)
    val seconds = _seconds.asStateFlow()

    fun start(seconds: Int = 60) {
        if (_seconds.value == 0) {
            _seconds.value = seconds
        }
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                if (_seconds.value <= 0) {
                    job?.cancel()
                    return@launch
                }
                delay(timeMillis = 1000)
                _seconds.value -= 1
            }
        }
    }

    fun pause() {
        job?.cancel()
    }

    fun stop() {
        job?.cancel()
        _seconds.value = 0
    }
}