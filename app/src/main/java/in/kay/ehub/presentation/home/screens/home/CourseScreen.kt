package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.Courses
import `in`.kay.ehub.presentation.auth.components.PrimaryButton
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

var globalNavController:NavController? = null

@Composable
fun CourseScreen2(viewModel: HomeViewModel,
                  paddingValues: PaddingValues,
                  navController: NavHostController) {


    var isCourseVisible = rememberSaveable {
        mutableStateOf(false)
    }

    /*
     * Setting the courses activities cards to UI
     */

    viewModel.coursesStateList.value.let {
        it.data?.let {
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<Courses>
                if (list.isNotEmpty()) {
                    isCourseVisible.value = true
                }
                viewModel.coursesList.value = list
                Log.d("backdatatest", "Courses: ${viewModel.coursesList}")
            })
        }
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFFFFFF))) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(text = "Courses",
                modifier = Modifier
                    .padding(start = 24.dp, top = 16.dp)
                    .fillMaxWidth(),
                style = Typography.h1)
            Text(text = "We provide the best courses by industry Experts!!",
                Modifier
                    .padding(start = 24.dp)
                    .fillMaxWidth(),style = Typography.body2, fontSize = 14.sp)

        }
        Spacer(modifier = Modifier.size(10.dp))
        if(isCourseVisible.value) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                state = rememberLazyListState()
            ) {
                itemsIndexed(viewModel.coursesList.value) { index, item ->
                    CourseCard(data = item,index,navController)
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
fun CourseCard(data:Courses,index:Int,navController: NavHostController){
Column {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(20.dp),
        elevation = 8.dp,
        backgroundColor = Color(0xFFFFFFFF),
    ) {
        Column {

            AsyncImage(
                model = if(Utils.getLastNCharsOfString(data.posterUrl,3)=="svg"){
                    ImageRequest.Builder(LocalContext.current)
                    .data(data.posterUrl)
                        .decoderFactory(SvgDecoder.Factory())
                    .error(R.drawable.ic_no_book)
                    .crossfade(true)
                    .build()   }else{
                    ImageRequest.Builder(LocalContext.current)
                        .data(data.posterUrl)
                        .error(R.drawable.ic_no_book)
                        .crossfade(true)
                        .build()
                                    },

                contentDescription = "Course image",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp, vertical = 15.dp),
                contentScale = ContentScale.Crop
            )
            Text(data.courseTitle,
                modifier = Modifier.padding(horizontal = 20.dp),
                fontSize = 20.sp
            )

            Text(data.courseAbout,
                modifier = Modifier.padding(horizontal = 20.dp),
                style = Typography.body2,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)

            ) {

                PrimaryButton(text = "View Course",
                    modifier = Modifier.padding(vertical = 19.dp, horizontal = 15.dp)
                    , onClick = {
                        navController.navigate(HomeNavRoutes.CourseDetails.route+"/${index}")
                    },
                    color = Color(0xFF002A36))
            }
        }

    }

}
    }

@Composable
fun CourseScreenTopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //This takes the remaining space other than icons but is not visible
        Text(
            text = "",
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
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "ic_profile",
            modifier = Modifier
                .weight(0.2f)
                .size(32.dp)
        )

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun preview(){
//    layout(paddingValues = )
    Card(
        modifier = Modifier
            .padding(vertical = 19.dp, horizontal = 15.dp)
            .fillMaxWidth()
        ,
        backgroundColor = Color(0xFF002A36),
        shape = RoundedCornerShape(100),
        onClick = {
//            navController.navigate(HomeNavRoutes.CourseDetails.route+"/${index}")
        }
    ) {
        Text(
            "View Course",
            Modifier.padding(vertical = 15.dp, horizontal = 36.dp),
            textAlign = TextAlign.Center,
            color = colorWhite

        )
    }
}

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 29.dp, end = 24.dp),
//                verticalAlignment = Alignment.Top,
//                horizontalArrangement = Arrangement.End
//            ){
//                Icon(
//                    imageVector = Icons.Outlined.Notifications,
//                    contentDescription = "this is a notification_bg_normal",
//                    modifier = Modifier
//                        .size(32.dp)
//                        .clip(CircleShape)
////                    contentScale = ContentScale.Crop
//                )
//                Spacer(modifier = Modifier.size(19.dp))
//                Image(
//                    painter = painterResource(R.drawable.profile),
//                    contentDescription = "this is a notification_bg_normal",
//                    modifier = Modifier
//                        .size(32.dp)
//                        .clip(CircleShape),
//                    contentScale = ContentScale.Crop
//                )
//
//            }