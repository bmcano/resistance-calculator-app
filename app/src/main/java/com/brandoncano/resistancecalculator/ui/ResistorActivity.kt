package com.brandoncano.resistancecalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.brandoncano.resistancecalculator.adapter.SharedPreferencesAdapter
import com.brandoncano.resistancecalculator.keys.AppAppearance
import com.brandoncano.resistancecalculator.navigation.ResistorNavigation
import com.brandoncano.resistancecalculator.ui.composables.AppAppearanceDialog
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResistorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sharedPreferencesAdapter = SharedPreferencesAdapter()

            val appAppearanceState = remember { mutableStateOf(AppAppearance.SYSTEM_DEFAULT) }
            val dynamicColorState = remember { mutableStateOf(false) }
            var showAppThemeDialog by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                val savedAppAppearance = withContext(Dispatchers.IO) {
                    sharedPreferencesAdapter.getAppAppearancePreference()
                }
                appAppearanceState.value = AppAppearance.valueOf(savedAppAppearance)
                dynamicColorState.value = withContext(Dispatchers.IO) {
                    sharedPreferencesAdapter.getDynamicColorPreference()
                }
            }

            ResistorCalculatorTheme(
                appAppearance = appAppearanceState.value,
                dynamicColor = dynamicColorState.value,
            ) {
                if (showAppThemeDialog) {
                    AppAppearanceDialog(
                        currentAppAppearance = appAppearanceState.value,
                        onAppAppearanceSelected = {
                            appAppearanceState.value = it
                            sharedPreferencesAdapter.setAppAppearancePreference(it.toString())
                        },
                        dynamicColor = dynamicColorState.value,
                        onDynamicColorSelected = {
                            dynamicColorState.value = it
                            sharedPreferencesAdapter.setDynamicColorPreference(it)
                        },
                        onDismissRequest = { showAppThemeDialog = false }
                    )
                }

                ResistorNavigation(
                    onOpenAppThemeDialog = { showAppThemeDialog = true },
                )
            }
        }
    }
}
