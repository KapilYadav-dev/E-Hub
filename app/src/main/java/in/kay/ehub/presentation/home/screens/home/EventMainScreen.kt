package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.Courses
import `in`.kay.ehub.domain.model.Events
import `in`.kay.ehub.presentation.home.components.EventsCard
import `in`.kay.ehub.presentation.home.components.NoDataFoundComponent
import `in`.kay.ehub.presentation.navigation.home.HomeNavRoutes
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorBlack
import `in`.kay.ehub.utils.Utils
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest



@Composable
fun EventMainScreen(viewModel: HomeViewModel,
                  paddingValues: PaddingValues,
                  navController: NavHostController) {


    var isEventsVisible = rememberSaveable {
        mutableStateOf(false)
    }

    /*
     * Setting the events cards to UI
     */


    //since this same thing is done in HomeViewModel so we are currently not doing it here again.

    viewModel.eventStateList.value.let {
        it.data?.let {
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<Events>
                if (list.isNotEmpty()) {
                    isEventsVisible.value = true
                }
                viewModel.eventsList.value = list
            })
        }
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)
        .background(Color(0xFFFFFFFF))) {

        Text(
            text = "Events",
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            style = Typography.h1)
        Spacer(modifier = Modifier.size(10.dp))

        if(isEventsVisible.value) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                state = rememberLazyListState(),

            ) {
                itemsIndexed(viewModel.eventsList.value) { index, item ->
                    val paddingStart = 0.dp
                    val paddingEnd = 0.dp

                    EventsCardMain(
                        event = item,
                        paddingStart = { paddingStart },
                        paddingEnd = { paddingEnd },
                        onProfileClick = {},
                        onItemClick = {
                            navController.navigate(HomeNavRoutes.Event.route) {
                                viewModel.itemIndex.value = index
                            }
                        },
                        { index }
                    )
                }
            }
        }else{

            Column(Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center) {
                NoDataFoundComponent()
            }

        }


    }
}


@Composable
fun EventsCardMain(
    event: Events,
    paddingStart: () -> Dp,
    paddingEnd: () -> Dp,
    onProfileClick: () -> Unit,
    onItemClick: () -> Unit,
    index: () -> Int
) {
    val gradient = listOf(
        Pair(
            Color(0xffAA076B),
            Color(0xff61045F)
        ),
        Pair(
            Color(0xff9400D3),
            Color(0xff4B0082)
        ),
        Pair(
            Color(0xff002A36),
            Color(0xff014051)
        ),
        Pair(
            Color(0xffEC008C),
            Color(0xffFC6767)
        )
    )
    val color = gradient[index().rem(gradient.size)]
    Column(
        Modifier
            .padding(
                paddingStart(),
                top = 8.dp,
                bottom = 8.dp,
                end = paddingEnd()
            )
            .fillMaxWidth()
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
            if (event.mentorImage?.isNotEmpty() == true && !event.mentorImage?.get(0).equals("")) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(event.mentorImage?.get(0))
                        .error(R.drawable.defaultmentor)
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
            }
            Column(
                Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = event.mentorName ?: " -",
                    style = Typography.body1,
                    color = colorWhite,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "${event.position} at ${event.company}",
                    style = Typography.body1,
                    color = colorWhite.copy(0.8f),
                    fontSize = 12.sp
                )
            }
        }
        Text(
            text = event.eventName.lowercase(),
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
        Text(
            text = Utils.getPresentableDate(event.eventDate),
            style = Typography.body1,
            color = colorWhite,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
        )
    }
}






