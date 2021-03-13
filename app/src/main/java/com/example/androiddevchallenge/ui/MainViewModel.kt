/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import android.util.Log
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

    companion object {
        const val SECONDS_INIT_VALUE = 60
    }

    private var job: Job? = null

    private val _seconds = MutableStateFlow(0)
    val seconds = _seconds.asStateFlow()

    private val _stopTimerVisibility = MutableStateFlow(false)
    val stopTimerVisibility = _stopTimerVisibility.asStateFlow()

    fun start() {
        _stopTimerVisibility.value = true

        Log.e("PIERRE", "start 1")

        if (_seconds.value == 0) {
            _seconds.value = SECONDS_INIT_VALUE
        }
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                if (_seconds.value <= 0) {
                    Log.e("PIERRE", "start 2")

                    _stopTimerVisibility.value = false
                    job?.cancel()
                    return@launch
                }
                Log.e("PIERRE", "start 3")

                delay(timeMillis = 1000)
                _seconds.value -= 1
            }
        }
    }

    fun pause() {
        Log.e("PIERRE", "pause")
        job?.cancel()
        _stopTimerVisibility.value = false
    }

    fun stop() {
        Log.e("PIERRE", "stop")

        job?.cancel()
        _seconds.value = 0
        _stopTimerVisibility.value = false
    }
}
