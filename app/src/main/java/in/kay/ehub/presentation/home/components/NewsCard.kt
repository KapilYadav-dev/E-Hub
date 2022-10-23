package `in`.kay.ehub.presentation.home.components

import `in`.kay.ehub.domain.model.Events
import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.presentation.home.screens.home.SectionHeader
import `in`.kay.ehub.presentation.home.screens.home.SubSectionHeader
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Utils
import androidx.compose.foundation.background
import `in`.kay.ehub.R
import `in`.kay.ehub.presentation.navigation.home.HomeNavRoutes
import `in`.kay.ehub.ui.theme.colorBlack
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.OriginalSize
import coil.size.Scale
import coil.size.Size

@Composable
fun NewsCard(
    news: News,
    paddingStart: () -> Dp,
    paddingEnd: () -> Dp,
    index: () -> Int,
    onItemClick:()->Unit
) {

    Box(modifier = Modifier
        .wrapContentHeight()
        .width(320.dp)
        .height(190.dp)
        .clickable {
            onItemClick()
        }
        .padding(start = paddingStart(), end = paddingEnd())
        .clip(RoundedCornerShape(16.dp)),
    contentAlignment = Alignment.BottomCenter){
//        Image(painter = painterResource(id = R.drawable.cimg),
//            contentDescription = "nothing",
//            contentScale = ContentScale.FillBounds,
//            modifier=Modifier.fillMaxSize())

        AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(news.imageUrl)
                    .error(R.drawable.ic_no_book)
                    .crossfade(true)
                    .build(),
                contentDescription = "news thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()

            )

        Box(contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.background(color = Color(0x33000000)).clip(
                RoundedCornerShape(16.dp))  ){
            Column(Modifier.padding(vertical = 4.dp)){
                Text(text=news.title,
                    Modifier.padding(start = 14.dp, end = 16.dp),
                    style = Typography.h1,
                    fontSize = 18.sp,
                    maxLines = 2,
                    color = colorWhite,
                    overflow = TextOverflow.Ellipsis)
                Text(text=news.description?:"",
                    Modifier.padding(start = 14.dp, end = 14.dp),
                    style = Typography.body2,
                    fontSize = 16.sp,
                    color = colorWhite,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
            }

        }
    }


}

@Composable
fun tempFun() {
    Box(modifier = Modifier
        .wrapContentHeight()
        .width(320.dp)
        .height(190.dp)
        .clickable {
        }
        .padding(start = 0.dp, end = 0.dp)
        .clip(RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.BottomCenter){
//        Image(painter = painterResource(id = R.drawable.cimg),
//            contentDescription = "nothing",
//            contentScale = ContentScale.FillBounds,
//            modifier=Modifier.fillMaxSize())

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.ic_no_book)
                .error(R.drawable.ic_no_book)
                .crossfade(true)
                .build(),
            contentDescription = "news thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.background(color = Color(0x22000000)).clip(
            RoundedCornerShape(16.dp)
        )){
            Column(Modifier.padding(vertical = 4.dp)){
                Text(text="helo guys my name iss aayush",
                    Modifier.padding(start = 14.dp, end = 16.dp),
                    style = Typography.h1,
                    fontSize = 18.sp,
                    maxLines = 2,
                    color = colorWhite,
                    overflow = TextOverflow.Ellipsis)
                Text(text="your name is what???",
                    Modifier.padding(start = 14.dp, end = 14.dp),
                    style = Typography.body2,
                    fontSize = 16.sp,
                    color = colorWhite,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis)
            }

        }
    }
}

@Preview
@Composable
fun previi() {
    tempFun()
}