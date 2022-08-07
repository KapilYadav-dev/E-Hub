package `in`.kay.ehub.presentation

import `in`.kay.ehub.presentation.auth.login.LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import `in`.kay.ehub.ui.theme.EhubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EhubTheme {
                LoginScreen()
            }
        }
    }
}