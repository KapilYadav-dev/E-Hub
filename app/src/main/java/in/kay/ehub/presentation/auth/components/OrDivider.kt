package `in`.kay.ehub.presentation.auth.components

import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorBorder
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OrDivider(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TabRowDefaults.Divider(
            color = colorBorder,
            thickness = 2.dp,
            modifier = Modifier.weight(1f)
        )
        Text(
            "or",
            modifier = Modifier
                .weight(0.2f)
                .padding(horizontal = 8.dp),
            style = Typography.body2,
            color = Color(0xff616161)
        )
        TabRowDefaults.Divider(
            color = colorBorder,
            thickness = 2.dp,
            modifier = Modifier.weight(1f))
    }
}