package com.abramoviclaura.composeui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.abramoviclaura.composeui.screen.bouncingball.BouncingBallScreen
import com.abramoviclaura.composeui.ui.theme.AndroidAnalysisUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidAnalysisUITheme {
                BouncingBallScreen()
            }
        }
    }
}
