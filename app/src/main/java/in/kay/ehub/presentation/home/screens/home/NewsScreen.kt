package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.Internship
import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.presentation.auth.components.PrimaryButton
import `in`.kay.ehub.presentation.home.components.ReadMoreText
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.ui.theme.Typography
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun NewsScreen(viewModel: HomeViewModel,
               navController:NavController,
               newsIndex:Int
               ) {

    var isNewsVisible = rememberSaveable {
        mutableStateOf(false)
    }
    val uriHandler = LocalUriHandler.current

    /*
     * Setting the internship activities cards to UI
     */
    viewModel.newsStateList.value.let {
        it.data?.let {
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<News>
                if (list.isNotEmpty()) {
                    isNewsVisible.value = true
                }
                viewModel.newsList.value = list
                Log.d("backdatatest", "News: ${viewModel.newsList}")
            })
        }
    }



    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFFFFFF))
        .padding(24.dp)
        .verticalScroll(rememberScrollState())){

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(viewModel.newsList.value[newsIndex].imageUrl)
                .error(R.drawable.ic_no_book)
                .crossfade(true)
                .build(),
            contentDescription = "data.bookTitle",
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Row (
            Modifier
                .wrapContentHeight()
                .padding(vertical = 24.dp)){
            Divider(
                color = Color.Black,
                modifier = Modifier
                    .height(38.dp)
                    .width(3.dp)

            )
            Text(text = viewModel.newsList.value[newsIndex].title,
                style = Typography.body1
                , modifier = Modifier.padding(start = 16.dp)
            )



        }

        Text(text = viewModel.newsList.value[newsIndex].description?:" "
            ,style= Typography.body1
        )
        ReadMoreText(text = viewModel.newsList.value[newsIndex].content?:"Content Not available...", modifier = Modifier.padding(top = 16.dp), style = Typography.body1)
        PrimaryButton(text = "Start Reading", modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp), color =  Color(0xFF002A36), onClick = {
                uriHandler.openUri(viewModel.newsList.value[newsIndex].link)
        })
    }

}

@Composable
fun another() {

}

@Preview
@Composable
fun prevv() {

}