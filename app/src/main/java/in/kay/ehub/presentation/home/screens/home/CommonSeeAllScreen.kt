package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.*
import `in`.kay.ehub.presentation.home.components.CampusActivitiesCard
import `in`.kay.ehub.presentation.home.components.NewsCard
import `in`.kay.ehub.presentation.home.components.NoDataFoundComponent
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.presentation.navigation.home.HomeNavRoutes
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Utils
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest


@Composable
fun CommonSeeAllScreen(
    viewModel: HomeViewModel,
    paddingValues: PaddingValues,
    navController:NavHostController,
    flag:String
) {

    var isEventsVisible = rememberSaveable {
        mutableStateOf(false)
    }
    var isNewsVisible = rememberSaveable {
        mutableStateOf(false)
    }
    var isCampusActivitiesVisible = rememberSaveable {
        mutableStateOf(false)
    }
    var isHandbooksVisible = rememberSaveable {
        mutableStateOf(false)
    }
    var isVideosVisible = rememberSaveable {
        mutableStateOf(false)
    }

    if (flag == "events") {

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


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .background(Color(0xFFFFFFFF))
        ) {

            Text(
                text = "Events",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                style = Typography.h1
            )
            Spacer(modifier = Modifier.size(10.dp))

            if (isEventsVisible.value) {

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
            } else {

                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    NoDataFoundComponent()
                }

            }


        }
    } else if (flag == "handbooks") {

        viewModel.handBookStateList.value.let {
            it.data?.let {
                LaunchedEffect(key1 = Unit, block = {
                    val list = it as List<Handbook>
                    if (list.isNotEmpty()) {
                        isHandbooksVisible.value = true
                    }
                    viewModel.handBookList.value = list
                })
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF)) //0xEEEEEEEE
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
//                Icon(
//                    imageVector = Icons.Filled.ArrowBack,
//                    contentDescription = "back-button",
//                    Modifier
//                        .size(36.dp)
//                        .align(Alignment.CenterVertically)
//                        .clickable(true) {
//                            navController.popBackStack()
//                        })
                Text(
                    text = "Handbooks",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    style = Typography.h1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }

            if (isHandbooksVisible.value) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    state = rememberLazyGridState(),
                    modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
                ) {
//                    val mList = viewModel.handBookList.value+viewModel.handBookList.value+viewModel.handBookList.value+viewModel.handBookList.value+viewModel.handBookList.value+viewModel.handBookList.value
                    itemsIndexed(viewModel.handBookList.value) { index, item ->
                        Column {
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
                                        start = 8.dp,
                                        top = 16.dp,
                                        end = 8.dp
                                    )
                                    .width(160.dp)
                                    .height(240.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable {
                                        navController.navigate(HomeNavRoutes.HandbookDetails.route + "/${index}")
                                    }
                            )
                            Text(
                                item.bookTitle,
                                modifier = Modifier.padding(start = 16.dp),
                                fontSize = 16.sp,
                                maxLines = 1
                            )
                            Text(
                                item.bookTagline,
                                modifier = Modifier.padding(start = 16.dp),
                                style = Typography.body2,
                                maxLines = 1
                            )
                        }
                    }
                }
            } else {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    NoDataFoundComponent()
                }
            }
        }
    } else if (flag == "campus") {

        viewModel.campusActivitiesStateList.value.let {
            it.data?.let {
                LaunchedEffect(key1 = Unit, block = {
                    val list = it as List<CampusActivities>
                    if (list.isNotEmpty()) {
                        isCampusActivitiesVisible.value = true
                    }
                    viewModel.campusActivitiesList.value = list
                })
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .background(Color(0xFFFFFFFF))
        ) {

            Text(
                text = "Campus Activities",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                style = Typography.h1
            )
            Spacer(modifier = Modifier.size(10.dp))

            if (isCampusActivitiesVisible.value) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = paddingValues.calculateBottomPadding()),
                    state = rememberLazyListState(),

                    ) {
                    itemsIndexed(viewModel.campusActivitiesList.value) { index, item ->
                        val paddingStart = 0.dp
                        val paddingEnd = 0.dp

                        CampusActivitiesCardSeeAll(
                            event = item,
                            paddingStart = { 0.dp },
                            paddingEnd = { 0.dp },
                            onProfileClick = { },
                            onItemClick = { },
                            index = { index }
                        )
                    }
                }
            }else {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    NoDataFoundComponent()
                }
            }
        }
    }
    else if (flag == "news") {

        viewModel.newsStateList.value.let {
            it.data?.let {
                LaunchedEffect(key1 = Unit, block = {
                    val list = it as List<News>
                    if (list.isNotEmpty()) {
                        isNewsVisible.value = true
                    }
                    viewModel.newsList.value = list
                })
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .background(Color(0xFFFFFFFF))
        ) {

            Text(
                text = "Tech Updates",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                style = Typography.h1
            )
            Spacer(modifier = Modifier.size(10.dp))

            if (isNewsVisible.value) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = paddingValues.calculateBottomPadding()),
                    state = rememberLazyListState(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)

                    ) {
                    itemsIndexed(viewModel.newsList.value) { index, item ->
                        val paddingStart = 0.dp
                        val paddingEnd = 0.dp

                        NewsCard(
                            news = item,
                            fillMaxWidth = true,
                            paddingStart = { 0.dp},
                            paddingEnd = { 0.dp },
                            index = { index},
                            onItemClick = {
                                navController.navigate(HomeNavRoutes.News.route + "/${index}")
                            }
                        )
                    }
                }
            }else {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    NoDataFoundComponent()
                }
            }
        }
    }
    else if (flag == "youtube") {

        viewModel.videoStateList.value.let {
            it.data?.let {
                LaunchedEffect(key1 = Unit, block = {
                    val list = it as List<YoutubeData>
                    if (list.isNotEmpty()) {
                        isVideosVisible.value = true
                    }
                    viewModel.videoList.value = list
                })
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .background(Color(0xFFFFFFFF))
        ) {

            Text(
                text = "Youtube Updates",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                style = Typography.h1
            )
            Spacer(modifier = Modifier.size(10.dp))

            if (isVideosVisible.value) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = paddingValues.calculateBottomPadding()),
                    state = rememberLazyListState(),

                    ) {
                    itemsIndexed(viewModel.videoList.value) { index, item ->
                        val paddingStart = 0.dp
                        val paddingEnd = 0.dp

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
                        Text(
                            item.videoTitle,
                            modifier = Modifier.padding(start = 16.dp,top=8.dp,bottom=24.dp),
                            fontSize = 20.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                    }
                }
            }else {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    NoDataFoundComponent()
                }
            }
        }
    }
}

@Composable
fun testing() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)) //0xEEEEEEEE
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back-button",
                Modifier
                    .size(36.dp)
                    .align(Alignment.CenterVertically)
                    .clickable(true) {
//                    navController.popBackStack()
                    })
            Text(
                text = "Magazines",
                fontSize = 27.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
                    .align(Alignment.Top),
                style = Typography.h1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(10){
                Column {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.drawable.ic_no_book)
                            .error(R.drawable.ic_no_book)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(
                                start = 8.dp,
                                top = 16.dp,
                                end = 8.dp
                            )
                            .width(160.dp)
                            .height(240.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
//                            Log.d("HOMESCREEN", "index of handbook: $index")
//                            navController.navigate(HomeNavRoutes.HandbookDetails.route + "/${index}")
                            }
                    )
                    Text(
                        "nothing",
                        modifier = Modifier,
                        fontSize = 16.sp,
                        maxLines = 1
                    )
                    Text(
                        "nothing either",
                        modifier = Modifier,
                        style = Typography.body2,
                        maxLines = 1
                    )
                }
            }
        }
    }

}

@Composable
fun CampusActivitiesCardSeeAll(
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
                //todo:change here later
                text = Utils.getDaysLeft(event.eventDate ?: "2022-10-08T00:00:00.000Z"),
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


@Preview
@Composable
fun test() {
    testing()
}

