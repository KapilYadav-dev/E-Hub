package `in`.kay.ehub.presentation.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AuthClickableText(
    modifier: Modifier,
    onClick: () -> Unit,
    secondaryText: String,
    primaryText: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = secondaryText,
            style = `in`.kay.ehub.ui.theme.Typography.body1,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = primaryText,
            style = `in`.kay.ehub.ui.theme.Typography.body1,
            fontWeight = FontWeight.SemiBold,
            color = `in`.kay.ehub.ui.theme.colorPrimary
        )
    }
}