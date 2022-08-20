package `in`.kay.ehub.presentation.auth.components

import `in`.kay.ehub.ui.theme.colorBorder
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun SecondaryButton(
    text: String,
    roundedCorner: Dp = 16.dp,
    modifier: Modifier,
    onClick: () -> Unit,
    painterResource: Painter? = null
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(roundedCorner))
            .border(
                2.dp, colorBorder,
                RoundedCornerShape(roundedCorner)
            ),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorWhite)
    ) {
        painterResource?.let {
            Image(
                painter = painterResource,
                contentDescription = "icIcon",
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text.lowercase(Locale.getDefault()),
            style = `in`.kay.ehub.ui.theme.Typography.body1,
            fontWeight = FontWeight.SemiBold
        )
    }
}