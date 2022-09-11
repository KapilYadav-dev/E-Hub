package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.*
import `in`.kay.ehub.presentation.auth.components.AppDialog
import `in`.kay.ehub.presentation.home.components.CampusActivitiesCard
import `in`.kay.ehub.presentation.home.components.EventsCard
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.presentation.navigation.home.HomeNavRoutes
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorBlack
import `in`.kay.ehub.ui.theme.colorWhite
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun HomeScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel
) {
    val context = LocalContext.current
    val parentScrollState = rememberScrollState()
    val activity = (LocalContext.current as? Activity)
    val user = viewModel.getUser(context)
    var isEventsVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var isCampusActivitiesVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var isHandBooksVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var isNewsVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var isInstaVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var isYoutubeVideosVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var username by remember {
        mutableStateOf("")
    }
    var backClicked by remember {
        mutableStateOf(false)
    }
    /*
     * Setting the user data from the Datastore to UI
     */
    user.collectAsState(initial = User()).value.let {
        username = it.userName
    }
    /*
     * Setting the events cards to UI
     */
    viewModel.eventStateList.value.let { it ->
        it.data?.let {
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<Events>
                if (list.isNotEmpty()) {
                    isEventsVisible = true
                }
                viewModel.eventsList.value = list
                Log.d("backdatatest", "HomeScreen: ${viewModel.eventsList}")
            })
        }
    }
    /*
     * Setting the campus activities cards to UI
     */
    viewModel.campusActivitiesStateList.value.let {
        it.data?.let {
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<CampusActivities>
                if (list.isNotEmpty()) {
                    isCampusActivitiesVisible = true
                }
                viewModel.campusActivitiesList.value = list
                Log.d("backdatatest", "HomeScreen: ${viewModel.campusActivitiesList}")
            })
        }
    }
    /*
     * Setting the handbooks cards to UI
     */
    viewModel.handBookStateList.value.let {
        it.data?.let {
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<Handbook>
                if (list.isNotEmpty()) {
                    isHandBooksVisible = true
                }
                viewModel.handBookList.value = list
                Log.d("backdatatest", "HomeScreen: ${viewModel.handBookList}")
            })
        }
    }
    /*
     * Setting the news cards to UI
     */
    viewModel.newsStateList.value.let {
        it.error.let { error ->
            if(error.isNotBlank()) {
                Log.d("backdatatest", "Error: ${error}")
            }
        }
        it.data?.let {
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<News>
                if (list.isNotEmpty()) {
                    isNewsVisible = true
                }
                viewModel.newsList.value = list
                Log.d("backdatatest", "HomeScreen: ${viewModel.newsList.value}")
                Toast.makeText(context, "Size is ${viewModel.newsList.value.size}", Toast.LENGTH_SHORT).show()
            })
        }
    }
    /*
     * Setting the instagram cards to UI
     */
    viewModel.videoStateList.value.let { it ->
        it.data?.let { it ->
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<YoutubeData>
                if (list.isNotEmpty()) {
                    isYoutubeVideosVisible = true
                }
                viewModel.videoList.value = list
            })
        }
    }

    if (backClicked) {
        AppDialog(
            modifier = Modifier.wrapContentWidth(),
            title = "Exit",
            message = "Are you sure you want to exit?",
            onDialogPositiveButtonClicked = {
                activity?.finish()
            },
            onDismissRequest = {
                backClicked = false
            })
    }
    BackHandler {
        backClicked = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(parentScrollState)
            .background(color = colorWhite)
    ) {
        TopSection(username)
        Text(
            text = "let's learn together.",
            style = Typography.body1,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF282020),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(horizontal = 24.dp)
        )
        if (isEventsVisible) {
            CardHeader(
                modifier = Modifier
                    .padding(top = 24.dp),
                cardTitle = "events", onSellAll = {

                })
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                itemsIndexed(viewModel.eventsList.value) { index, item ->
                    val paddingStart = if (index == 0) 24.dp else 4.dp
                    val paddingEnd =
                        if (index == viewModel.eventsList.value.size - 1) 24.dp else 0.dp
                    EventsCard(
                        event = item,
                        paddingStart = { paddingStart },
                        paddingEnd = { paddingEnd },
                        onProfileClick = {},
                        onItemClick = {},
                        { index }
                    )
                }
            }
        }
        if (isCampusActivitiesVisible) {
            CardHeader(
                cardTitle = "campus activities", onSellAll = {

                })
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                itemsIndexed(viewModel.campusActivitiesList.value) { index, item ->
                    val paddingStart = if (index == 0) 24.dp else 4.dp
                    val paddingEnd =
                        if (index == viewModel.campusActivitiesList.value.size - 1) 24.dp else 0.dp
                    CampusActivitiesCard(
                        event = item,
                        paddingStart = { paddingStart },
                        paddingEnd = { paddingEnd },
                        onProfileClick = {},
                        onItemClick = {},
                        index = { index }
                    )
                }
            }
        }
        if (isHandBooksVisible) {
            CardHeader(
                cardTitle = "handbooks", onSellAll = {

                })
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                itemsIndexed(viewModel.handBookList.value) { index, item ->
                    val paddingStart = if (index == 0) 24.dp else 4.dp
                    val paddingEnd =
                        if (index == viewModel.handBookList.value.size - 1) 24.dp else 0.dp
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.bookimgUrl)
                            .error(R.drawable.ic_no_book)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(
                                start = paddingStart,
                                top = 10.dp,
                                bottom = 24.dp,
                                end = paddingEnd
                            )
                            .width(160.dp)
                            .height(240.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {

                            }
                    )
                }
            }
        }
        if (isYoutubeVideosVisible) {
            CardHeader(
                cardTitle = "youtube updates", onSellAll = {

                })
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                itemsIndexed(viewModel.videoList.value) { index, item ->
                    val paddingStart = if (index == 0) 24.dp else 4.dp
                    val paddingEnd =
                        if (index == viewModel.videoList.value.size - 1) 24.dp else 0.dp
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.thumbnails[0].medium.url)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(
                                start = paddingStart,
                                top = 10.dp,
                                bottom = 24.dp,
                                end = paddingEnd
                            )
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                viewModel.itemIndex.value = index
                                navController.navigate(HomeNavRoutes.Youtube.route)
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun CardHeader(modifier: Modifier = Modifier, cardTitle: String, onSellAll: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = cardTitle,
            style = Typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
        Text(
            text = "see all",
            style = Typography.body1,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF282020),
            modifier = Modifier.clickable {
                onSellAll()
            }
        )
    }
}


@Composable
fun TopSection(username: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Hi, $username",
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