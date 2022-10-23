package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.Courses
import `in`.kay.ehub.domain.model.Internship
import `in`.kay.ehub.presentation.home.components.ReadMoreText
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.presentation.navigation.home.HomeNavRoutes
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorBlack
import `in`.kay.ehub.ui.theme.colorWhite
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun InternshipScreen(
    viewModel: HomeViewModel,
    paddingValues: PaddingValues,
    navController: NavHostController
) {

    var isInternshipVisible = rememberSaveable {
        mutableStateOf(false)
    }


    /*
     * Setting the internship activities cards to UI
     */
    viewModel.internshipStateList.value.let {
        it.data?.let {
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<Internship>
                if (list.isNotEmpty()) {
                    isInternshipVisible.value = true
                }
                viewModel.internshipList.value = list
                Log.d("backdatatest", "Internships: ${viewModel.internshipList}")
            })
        }
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFFFFFF))){
//        InternshipTopSection()
        Text(text = "Internships",
            modifier = Modifier
                .padding(start = 24.dp, top = 16.dp, end = 24.dp)
                .fillMaxWidth(),
            style = Typography.h1)
        if(isInternshipVisible.value){

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                state = rememberLazyListState()
            ) {
                itemsIndexed(viewModel.internshipList.value){ index,item->
                    InternshipCard(data = item)
                }


            }
        }
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InternshipCard(data:Internship){

        // To handle links
        val uriHandler = LocalUriHandler.current

        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(20.dp),
            elevation = 8.dp,
            backgroundColor = Color(0xFFFFFFFF),
        ) {
            Column(
                Modifier
                    .wrapContentSize()
                    .padding(8.dp)) {

                Row(modifier = Modifier.fillMaxWidth()){
                    Image(
                        painter = painterResource(id = R.drawable.internshipimage),
                        contentDescription = "Internship image",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(4.dp)

                    )
                    Text(text=data.internPosition,
                        style = Typography.h1,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                    Divider(
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 4.dp)
                            .height(20.dp)
                            .width(2.dp)
                    )
                    Text(text=data.internCompany,
                        style = Typography.h1,
                        fontSize = 16.sp,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                }

                Row(modifier = Modifier
                    .padding(start = 12.dp)
                    .fillMaxWidth()
                        ){
                        CustomForInternship(text=data.timing)
                        Spacer(modifier = Modifier.padding(horizontal = 16.dp))
                        CustomForInternship(text="${data.location},india")
                        Spacer(modifier = Modifier.padding(horizontal = 16.dp))
                        CustomForInternship(text=data.type)
                    }


                ReadMoreText(text = data.description,
                    modifier = Modifier.padding(start = 12.dp, end = 30.dp, top = 12.dp, bottom = 4.dp),
                    style = Typography.body2,
                    )
//                Text(
//                    modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 12.dp, bottom = 4.dp),
//                    text = data.description,
//                    fontSize = 16.sp,
//                    style = Typography.body2,
//                    maxLines = 4,
//                    overflow = TextOverflow.Ellipsis,
//                )

                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Card(
                        modifier = Modifier
                            .padding(top = 12.dp, bottom = 4.dp, start = 12.dp, end = 12.dp)
                            .fillMaxWidth(),
                        backgroundColor = Color(0xFF002A36),
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
                                uriHandler.openUri(data.applyLink)
                        }
                    ) {
                            Text(
                                modifier = Modifier.padding(
                                    vertical = 9.dp,
                                    horizontal = 36.dp),
                                text="Apply",
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                color = colorWhite
                            )
                    }
                }
            }
        }
    }

@Composable
fun CustomForInternship(text:String){
    Row {
        Column(modifier = Modifier
            .padding(top = 6.dp)
        ) {
            Canvas(modifier = Modifier.size(4.dp), onDraw = {
                drawCircle(color = Color.Black)
            })
        }
        Column(modifier = Modifier
            .padding(start = 5.dp)
            .wrapContentWidth()
            .align(Alignment.CenterVertically)) {
            Text(text = text, fontSize = 14.sp, style = Typography.body1)
        }
    }
}

@Composable
fun InternshipTopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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

@Preview(showBackground = true)
@Composable
fun InternshipScreenPreview() {

}