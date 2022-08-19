package `in`.kay.ehub.presentation.home

import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorBlack
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun HomeScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorWhite)
            .padding(horizontal = 24.dp)
    ) {
        TopSection()
        Text(
            text = "let's learn together.",
            style = Typography.body1,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF282020),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        Text(
            text = "events",
            style = Typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {

        }
    }
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Hi, mrkaydev",
            style = Typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "ic_notifications",
            tint = colorBlack,
            modifier = Modifier
                .weight(0.2f)
                .size(32.dp)
        )
        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = "ic_profile",
            tint = colorBlack,
            modifier = Modifier
                .weight(0.2f)
                .size(32.dp)
        )
    }
}