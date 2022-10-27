package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.Domains
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.presentation.lifecycle.rememberLifecycleEvent
import `in`.kay.ehub.presentation.navigation.home.HomeNavRoutes
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DomainScreen(
    viewModel: HomeViewModel,
    navController: NavHostController,
    paddingValues:PaddingValues
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFFFFFFF))) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(text = "Domains",
                modifier = Modifier
                    .padding(start = 24.dp, top = 16.dp)
                    .fillMaxWidth(),
                style = Typography.h1)

        }
        Spacer(modifier = Modifier.size(10.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = paddingValues.calculateBottomPadding(), start = 24.dp, end = 24.dp),
                state = rememberLazyListState(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                //TODO: change data from domainsList() to real domains data from api(if told)
                  itemsIndexed(items = domainsList()){index,item->
                      DomainElementCard(item, onItemClicked =
                      {
                          navController.navigate(HomeNavRoutes.DomainSubScreen.route) {
                              viewModel.itemIndex.value = index
                          }
                      }
                      )
                  }
            }



    }
}


@Composable
fun DomainElementCard(data:Domains,
                      onItemClicked:()->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                       onItemClicked()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        backgroundColor = colorWhite
    ){
        Row(modifier = Modifier.padding(8.dp)){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.imgUrl)
                    .error(R.drawable.ic_no_book)
                    .crossfade(true)
                    .build(),

//                painter = painterResource(id = R.drawable.cimg),
                contentDescription = "Domain image",
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Text(text= data.name,
                modifier = Modifier.align(Alignment.CenterVertically)
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun prevvv() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        backgroundColor = colorWhite
    ){
        Row(modifier = Modifier.padding(8.dp)){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(domainsList()[0].imgUrl)
                    .error(R.drawable.ic_no_book)
                    .crossfade(true)
                    .build(),
//                painter = painterResource(id = R.drawable.cimg),
                contentDescription = "Domain image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Text(text= domainsList()[0].name,
                modifier = Modifier.align(Alignment.CenterVertically)
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1)
        }
    }
}


//@Composable
//fun DomainScreen(viewModel: HomeViewModel, navController: NavHostController) {
//    var isPgOne by remember {
//        mutableStateOf(true)
//    }
//    var isPgTwo by remember {
//        mutableStateOf(false)
//    }
//    var isPgThree by remember {
//        mutableStateOf(false)
//    }
//    val lifecycle = rememberLifecycleEvent()
//    LaunchedEffect(lifecycle) {
//        if (lifecycle == Lifecycle.Event.ON_RESUME) {
//            isPgOne = true
//            isPgTwo = false
//            isPgThree = false
//        }
//    }
//    if (isPgOne) DomainPGOne()
//    if (isPgTwo) DomainPGTwo()
//    if (isPgThree) DomainPGThree()
//}
//
//
//@Preview
//@Composable
//fun show(){
//    DomainPGOne()
//}
//
//@Composable
//fun DomainPGThree() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorWhite)
//    ) {
//
//    }
//}
//
//@Composable
//fun DomainPGTwo() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorWhite)
//    ) {
//
//    }
//}
//
//@Composable
//fun DomainPGOne() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorWhite)
//    ) {
//        Text(text = "domains", style = Typography.h1 )
//    }
//}

fun domainsList() = listOf(
    Domains(
        "Data Structures & Algorithms",
        "https://play-lh.googleusercontent.com/9zvNJHedNg_6lOdwcodODMVsyeHKxuTIpnbBzomRGGZAp_vKVXnd5SlF8XZcXyGYjQ"
    ),
    Domains(
        "Web Development",
        "https://cdn-icons-png.flaticon.com/512/2210/2210153.png"),
    Domains(
        "App Development",
        "https://static.vecteezy.com/system/resources/previews/006/867/251/large_2x/service-cogwheels-inside-mobile-gradient-of-mobile-app-development-vector.jpg"
    ),
    Domains(
        "Machine Learning & AI",
        "https://cdn5.vectorstock.com/i/1000x1000/43/64/machine-learning-icon-artificial-intelligence-vector-35234364.jpg"
    ),
    Domains(
        "Cyber Security",
        "https://img.freepik.com/free-vector/global-data-security-personal-data-security-cyber-data-security-online-concept-illustration-internet-security-information-privacy-protection_1150-37375.jpg?w=200"
    ),
    Domains(
        "UI/UX Design",
        "https://img.freepik.com/free-vector/ui-ux-app-development-concept_52683-48848.jpg?w=200"
    ),
    Domains(
        "Block Chain",
        "https://img.freepik.com/free-psd/3d-nft-icon-chain_629802-28.jpg"
    ),
    Domains(
        "DevOps",
        "https://media.istockphoto.com/vectors/devops-symbol-and-icon-software-development-operations-concept-vector-id1204555368?k=20&m=1204555368&s=612x612&w=0&h=nSX-5MTJ93AVswECHX4fXGirunsz-v5XbUwqoFQNOiE="
    )
)