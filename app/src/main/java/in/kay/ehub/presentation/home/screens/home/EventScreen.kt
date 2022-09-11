package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.R
import `in`.kay.ehub.presentation.auth.components.PrimaryButton
import `in`.kay.ehub.presentation.auth.components.SecondaryButton
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Constants
import `in`.kay.ehub.utils.Utils.getDaysLeftInt
import android.text.Html
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun EventScreen(
    viewModel: HomeViewModel,
    navController: NavHostController
) {
    val item = viewModel.eventsList.value[viewModel.itemIndex.value]
    val joinSessionEnabled = isSessionGoingToStart(item.eventDate) && item.eventCode.isNotBlank() && item.eventCode.contains(Constants.MEET_CODE)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorWhite)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .padding(start = 16.dp, top = 32.dp)
                    .size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Event",
                style = Typography.body1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 32.dp, end = 16.dp),
                fontSize = 24.sp
            )
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.posterUrl)
                .error(R.drawable.ic_no_book)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 32.dp
                )
                .fillMaxWidth()
                .height(420.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable {

                }
        )
        Text(
            text = "about event",
            style = Typography.body1,
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp, top = 24.dp, end = 16.dp),
            fontSize = 18.sp
        )
        Text(
            text = Html.fromHtml(item.description, Html.FROM_HTML_MODE_LEGACY).toString(),
            style = Typography.body1,
            textAlign = TextAlign.Justify,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 32.dp),
            fontSize = 16.sp
        )
        PrimaryButton(
            text = "join session", modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(56.dp),
            onClick = {},
            isEnabled = joinSessionEnabled,
            color = Color(0xff002B36)
        )
        SecondaryButton(
            "save for later",
            modifier = Modifier
                .padding(16.dp)
                .height(56.dp),
            onClick = {

            },
            buttonIconSize = 40.dp,
            color = Color(0xff002B36)
        )
    }
}

fun isSessionGoingToStart(eventDate: String): Boolean {
    // This showing that event is going to start in less than 10 mins
    if (getDaysLeftInt(eventDate) < 1) {
        return true
    }
    return false
}
