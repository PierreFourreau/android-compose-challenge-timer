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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.components.Chrono
import com.example.androiddevchallenge.ui.components.Pause
import com.example.androiddevchallenge.ui.components.Start
import com.example.androiddevchallenge.ui.components.Stop
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                val viewModel by viewModels<MainViewModel>()
                val seconds by viewModel.seconds.collectAsState()
                MyApp(
                    seconds = seconds,
                    onPause = { viewModel.pause() },
                    onStart = { viewModel.start() },
                    onStop = { viewModel.stop() }
                )
            }
        }
    }
}

@Composable
fun MyApp(seconds: Int, onPause: () -> Unit, onStart: () -> Unit, onStop: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "My beautiful timer",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxWidth()
                )
                Chrono(seconds)
                ControlButtons(
                    onPause = { onPause.invoke() },
                    onStart = { onStart.invoke() },
                    onStop = { onStop.invoke() }
                )
            }
        }
    }
}

@Composable
fun ControlButtons(
    modifier: Modifier = Modifier,
    onPause: () -> Unit,
    onStart: () -> Unit,
    onStop: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Pause(onPause = onPause)
        Start(onStart = onStart)
        Stop(onStop = onStop)
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp(4, {}, {}, {})
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(4, {}, {}, {})
    }
}
