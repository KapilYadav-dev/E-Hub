package `in`.kay.ehub.presentation

import `in`.kay.ehub.presentation.navigation.auth.AuthNavigationContainer
import `in`.kay.ehub.ui.theme.EhubTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EhubTheme {
                AuthNavigationContainer()
            }
        }
    }
}
