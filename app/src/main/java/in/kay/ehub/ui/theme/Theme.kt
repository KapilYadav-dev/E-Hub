package `in`.kay.ehub.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val LightColorPalette = lightColors(
    primary = colorPrimary,
    primaryVariant = colorPrimary,
    secondary = colorPrimary,
    error = colorError

)

@Composable
fun EhubTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}