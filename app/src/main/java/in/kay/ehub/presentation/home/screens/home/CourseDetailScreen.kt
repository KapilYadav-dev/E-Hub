package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.Courses
import `in`.kay.ehub.domain.model.Syllabus
import `in`.kay.ehub.presentation.home.components.ReadMoreText
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorBlack
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Utils
import `in`.kay.ehub.utils.Utils.getDate
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun CourseDetailScreen(
    viewModel: HomeViewModel,
    navController: NavHostController,
    courseIndex:Int? = null
) {

    val currentCourse = viewModel.coursesList.value[courseIndex!!]


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)) //0xEEEEEEEE
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {

        TopSection(navController,currentCourse)
        CourseCardDetails(currentCourse)
        SectionHeader(text = "Mentor")
        MentorCard(currentCourse)
        SectionHeader(text = "About Course")
        AboutCard(currentCourse)
        SectionHeader(text = "Syllabus")
        SyllabusCard(currentCourse)
        SectionHeader(text = "Features")
        FeaturesCard(currentCourse)
        SectionHeader(text = "Curriculum")
        SubSectionHeader()
        LecturesList(currentCourse)
    }
}



@Composable
fun TopSection(navController: NavHostController,currentCourse: Courses){
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "back-button",
            Modifier
                .size(36.dp)
                .align(Alignment.CenterVertically)
                .clickable(true) {
                    navController.popBackStack()
                })
        Text(text = currentCourse.courseTitle,
            fontSize=27.sp,
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth()
                .align(Alignment.Top),
            style = Typography.h1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis)

    }


}


@Composable
fun CourseCardDetails(currentCourse:Courses){
        Card(
            modifier = Modifier
                .padding(top = 16.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(20.dp),
            elevation = 8.dp,
            backgroundColor = Color(0xFFFFFFFF),
        ) {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(currentCourse.courseImgUrls[0])
                        .error(R.drawable.ic_no_book)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Course image",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 15.dp),
                    contentScale = ContentScale.Crop
                )
                Text(currentCourse.courseTitle, modifier = Modifier.padding(horizontal = 12.dp),fontSize = 30.sp, style = Typography.h1)
                Text(
                    currentCourse.courseAbout,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
                    style = Typography.body1,
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )


            }

        }

    }


@Composable
fun SectionHeader(
    text:String
){
    Text(text=text,Modifier.padding(start = 14.dp, top = 24.dp,bottom=8.dp), style = Typography.h1, fontSize = 20.sp)
}

@Composable
fun MentorCard(currentCourse: Courses){

    Card(
        modifier = Modifier
            .padding(top = 5.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        backgroundColor = Color(0xFFFFFFFF),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
            verticalAlignment = Alignment.CenterVertically) {

//            Image(painter = painterResource(id = R.drawable.demomentor),
//                contentDescription = "Mentor image",
//            Modifier.size(55.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(currentCourse.courseMentorImage[0])
                    .error(R.drawable.ic_no_book)
                    .crossfade(true)
                    .build(),
//                painter = painterResource(id = R.drawable.cimg),
                contentDescription = "Mentor image",
                modifier = Modifier
                    .size(55.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(Modifier.padding(top = 20.dp, bottom = 20.dp, end = 20.dp, start = 10.dp)) {
                Text(text = currentCourse.courseMentorName, style = Typography.h1, fontSize = 20.sp)
                Text(text = currentCourse.courseMentorPosition+" at "+ currentCourse.courseMentorCompany, style = Typography.body1, fontSize = 12.sp)
            }

        }

    }

}

@Composable
fun AboutCard(currentCourse: Courses){
    Card(
        modifier = Modifier
            .padding(top = 5.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        backgroundColor = Color(0xFFFFFFFF),
    ){
        ReadMoreText(text = currentCourse.courseAbout,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(), style = Typography.body1)

    }
}

@Composable
fun SyllabusCard(currentCourse: Courses){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        backgroundColor = Color(0xFFFFFFFF),
    ){
//        LazyColumn{
//            items(10){
//
//            }
//        }
        Column(Modifier.padding(10.dp)) {
            currentCourse.courseSyllabus.forEach {
                SyllabusElement(it)
                Spacer(modifier = Modifier.size(2.dp))
            }

        }
    }
}

@Composable
fun SyllabusElement(syllabusElement:Syllabus){
    Row(modifier = Modifier.padding()){
        Column(modifier = Modifier
            .padding(top = 8.dp, start = 5.dp)) {
            Canvas(modifier = Modifier.size(4.dp), onDraw = {
                drawCircle(color = Color.Black)
            })
        }
        Column(modifier = Modifier
            .padding(start = 5.dp)
            .wrapContentWidth()) {
            Text(text = "DAY ${syllabusElement.day}",
                fontWeight = Bold, fontSize = 16.sp)
            Text(text = syllabusElement.title , fontSize = 14.sp)
        }
    }
}

@Composable
fun FeaturesCard(currentCourse: Courses){
    Card(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        backgroundColor = Color(0xFFFFFFFF),
    ){
        Column(Modifier.padding(10.dp)
        ) {
            currentCourse.features.forEach{
                FeatureElement(it)
                Spacer(modifier = Modifier.size(2.dp))
            }

        }
    }
}
@Composable
fun FeatureElement(text:String){
    Row(modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier
            .padding(top = 8.dp, start = 5.dp)) {
            Canvas(modifier = Modifier.size(4.dp), onDraw = {
                drawCircle(color = Color.Black)
            })
        }
        Column(modifier = Modifier
            .padding(start = 5.dp)) {
            Text(text = text, fontSize = 16.sp)
        }
    }
}

@Composable
fun SubSectionHeader(){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 14.dp)){
        Text(text = "10 Lectures", fontSize = 16.sp,)
        Column(Modifier.padding(start = 5.dp, end = 5.dp, top = 9.dp)) {
            Canvas(modifier = Modifier.size(4.dp), onDraw = {
                drawCircle(color = Color.Black)
            })
        }
        Text(text = "10 Hours 50 Mins", fontSize = 16.sp)
    }
}

@Composable
fun LecturesList(currentCourse: Courses){
    Column {

        Log.d("COURSEDETAILS",currentCourse.courseSyllabus.size.toString())
        currentCourse.courseSyllabus.forEach{
            LectureElement(it)

        }

    }
}

@Composable
fun LectureElement(syllabusElement:Syllabus){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        backgroundColor = Color(0xFF002A36)
    ){
        Row(horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(start = 10.dp)){
            Image(painter = painterResource(id = R.drawable.youtuberec), contentDescription = "YoutubeIconForCourse",
            Modifier.size(60.dp))
                Box {
                    Text(text = syllabusElement.day.toString(), fontSize = 30.sp, color = colorWhite)
                }
            }

            Column(
                Modifier
                    .padding(top = 5.dp, bottom = 5.dp, end = 5.dp, start = 5.dp)
                    .weight(2f)) {
                Text(text = "Lecture ${syllabusElement.day}", fontSize = 20.sp, color = colorWhite)
                Text(text = syllabusElement.title, maxLines = 1,
                    fontSize = 16.sp, color = colorWhite,
                    overflow = TextOverflow.Ellipsis)
            }

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 5.dp, end = 20.dp)
                    .clickable {
                        //TODO: start youtube view here
                    }){
                Image(painter = painterResource(id = R.drawable.play), contentDescription = "playBackground",
                    Modifier.size(50.dp))
                Box(Modifier.padding(start = 6.dp)){
                    Image(painter = painterResource(id = R.drawable.playsmall), contentDescription = "playForeground",
                        Modifier.size(30.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CourseDetailsPreview(){


}