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
import com.brandoncano.resistancecalculator.navigation.Navigation
import com.brandoncano.resistancecalculator.ui.composables.AppAppearanceDialog
import com.brandoncano.resistancecalculator.ui.theme.ResistorCalculatorTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sharedPreferencesAdapter = SharedPreferencesAdapter()

            val appAppearanceState = remember { mutableStateOf(AppAppearance.SYSTEM_DEFAULT) }
            var showAppThemeDialog by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                val savedAppAppearance = withContext(Dispatchers.IO) {
                    sharedPreferencesAdapter.getAppAppearancePreference()
                }
                appAppearanceState.value = AppAppearance.valueOf(savedAppAppearance)
            }

            ResistorCalculatorTheme(appAppearance = appAppearanceState.value) {
                if (showAppThemeDialog) {
                    AppAppearanceDialog(
                        currentAppAppearance = appAppearanceState.value,
                        onThemeSelected = {
                            sharedPreferencesAdapter.setAppAppearancePreference(it.toString())
                            appAppearanceState.value = it
                        },
                        onDismissRequest = { showAppThemeDialog = false }
                    )
                }

                Navigation(
                    onOpenAppThemeDialog = { showAppThemeDialog = true },
                )
            }
        }
    }
}
