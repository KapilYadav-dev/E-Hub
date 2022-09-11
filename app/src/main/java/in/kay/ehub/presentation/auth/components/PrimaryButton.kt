package `in`.kay.ehub.presentation.auth.components

import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorPrimary
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier,
    isEnabled: Boolean = true,
    roundedCorner: Dp = 16.dp,
    onClick: () -> Unit,
    color: Color = colorPrimary
) {
    Button(
        onClick = { onClick() },
        enabled = isEnabled,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(roundedCorner))
            .background(color = color),
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text(
            text.lowercase(Locale.getDefault()),
            style = Typography.body1,
            fontWeight = FontWeight.SemiBold,
            color = colorWhite
        )
    }
}