package com.kaleksandra.composetemplate

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kaleksandra.corenavigation.GamepadDirection
import com.kaleksandra.coretheme.AppTheme
import com.kaleksandra.featuregamepad.presentation.ControllerScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {
            AppTheme {
                val viewModel: MainViewModel = hiltViewModel()
                val isAuthorized by viewModel.isAuthorized.collectAsState()
                val startDestination = GamepadDirection.path
                SetStatusBarColor(
                    MaterialTheme.colorScheme.background,
                    !isSystemInDarkTheme()
                )
                val navController = rememberNavController()
                Scaffold {
                    NavHost(
                        navController = navController,
                        startDestination = startDestination,
                    ) {
                        composable(GamepadDirection.path) { ControllerScreen(navController = navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun SetStatusBarColor(
    color: Color,
    darkIcons: Boolean = true
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color, darkIcons)
        systemUiController.setNavigationBarColor(color, darkIcons)
    }
}