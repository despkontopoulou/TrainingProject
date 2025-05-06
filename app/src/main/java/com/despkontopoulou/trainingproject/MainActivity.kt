package com.despkontopoulou.trainingproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.despkontopoulou.trainingproject.ui.screens.LoginScreen
import com.despkontopoulou.trainingproject.ui.screens.MagazineScreen
import com.despkontopoulou.trainingproject.ui.theme.TrainingProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TrainingProjectTheme {
                var token by remember { mutableStateOf<String?>(null) }
                Surface {
                    if (token == null) {
                        LoginScreen { _, _, earnedToken ->
                            token = earnedToken.toString()
                        }
                    } else {
                        MagazineScreen(bearerToken = token!!)
                    }
                }
            }
        }
    }
}
