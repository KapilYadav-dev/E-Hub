package `in`.kay.ehub.presentation.auth.components

import `in`.kay.ehub.ui.theme.colorBorder
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun SecondaryButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .border(
                2.dp, colorBorder,
                RoundedCornerShape(16.dp)
            ),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorWhite)
    ) {
        Text(
            text.lowercase(Locale.getDefault()),
            style = `in`.kay.ehub.ui.theme.Typography.body1,
            fontWeight = FontWeight.SemiBold
        )
    }
}