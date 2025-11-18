package com.easyhooon.metroapplication.feature

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.easyhooon.metroapplication.core.di.ActivityKey
import com.easyhooon.metroapplication.feature.screens.SplashScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding

@ContributesIntoMap(AppScope::class, binding = binding<Activity>())
@ActivityKey(MainActivity::class)
@Inject
class MainActivity(
    private val circuit: Circuit,
) : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val backStack = rememberSaveableBackStack(root = SplashScreen)
                    val navigator = rememberCircuitNavigator(backStack)

                    CircuitCompositionLocals(circuit) {
                        NavigableCircuitContent(
                            navigator = navigator,
                            backStack = backStack,
                            modifier = Modifier.fillMaxSize(),
                        )
                    }
                }
            }
        }
    }
}