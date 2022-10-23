package `in`.kay.ehub.presentation.home.components

import `in`.kay.ehub.domain.model.CampusActivities
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Utils.getDaysLeft
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CampusActivitiesCard(
    event: CampusActivities,
    paddingStart: () -> Dp,
    paddingEnd: () -> Dp,
    onProfileClick: () -> Unit,
    onItemClick: () -> Unit,
    index: () -> Int
) {
    val gradient = listOf(
        Pair(
            Color(0xFF021B79),
            Color(0xff0575E6)
        ),

        Pair(
            Color(0xff9400D3),
            Color(0xff4B0082)
        ),
        Pair(
            Color(0xffEC008C),
            Color(0xffFC6767)
        ),
        Pair(
            Color(0xffAA076B),
            Color(0xff61045F)
        ),
        Pair(
            Color(0xff002A36),
            Color(0xff014051)
        )
    )
    val color = gradient[index().rem(gradient.size)]
    Column(
        Modifier
            .padding(paddingStart(), top = 10.dp, bottom = 24.dp, end = paddingEnd())
            .width(320.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        color.first,
                        color.second
                    ),
                    start = Offset(0.0f, 0.0f),
                    end = Offset(800f, 0.0f)
                )
            )
            .padding(4.dp)
            .clickable {
                onItemClick()
            }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(event.collegePhoto[0])
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .clickable {
                        onProfileClick()
                    }
            )
            Column(
                Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = event.collegeName,
                    style = Typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = colorWhite,
                    fontWeight = FontWeight.Thin
                )
                Text(
                    text = event.eventType,
                    style = Typography.body1,
                    fontWeight = FontWeight.Bold,
                    color = colorWhite.copy(1f),
                    fontSize = 12.sp
                )
            }
        }
        Text(
            text = event.eventName,
            style = Typography.body1,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = colorWhite,
            fontWeight = FontWeight.SemiBold
        )
        Divider(
            color = Color.White.copy(0.6f),
            thickness = 1.dp,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 48.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .padding(start = 16.dp, bottom = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = getDaysLeft(event.eventDate),
                style = Typography.body1,
                color = colorWhite,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
            )
            Card(
                modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                elevation = 8.dp,
                backgroundColor = Color(0xffFF7A00),
            ) {
                Text(
                    text = "Prize $ ${event.price}",
                    style = Typography.body1,
                    color = colorWhite,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                )

            }
        }
    }
}
